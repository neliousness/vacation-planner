package com.pulsar.vacationplanner.presentation.home

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulsar.vacationplanner.data.model.destinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.domain.common.Result
import com.pulsar.vacationplanner.domain.exceptions.DestinationFetchException
import com.pulsar.vacationplanner.domain.exceptions.ItineraryFetchException
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import com.pulsar.vacationplanner.domain.usecases.SearchItineraryUseCase
import com.pulsar.vacationplanner.domain.usecases.FetchDestinationsUseCase
import com.pulsar.vacationplanner.util.extensions.toLocationItinerary
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getItinerariesUseCase: SearchItineraryUseCase,
    private val fetchDestinationsUseCase: FetchDestinationsUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<HomeEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _locationItineraries = MutableStateFlow<List<LocationItinerary>>(emptyList())
    val locationItineraries: StateFlow<List<LocationItinerary>> = _locationItineraries.asStateFlow()

    private val _affordableLocationItineraries =
        MutableStateFlow<List<LocationItinerary>>(emptyList())
    val affordableLocationItineraries: StateFlow<List<LocationItinerary>> =
        _affordableLocationItineraries.asStateFlow()

    private val _isPopularLoading = MutableStateFlow(true)
    val isPopularLoading: StateFlow<Boolean> = _isPopularLoading.asStateFlow()

    private val _isAffordableLoading = MutableStateFlow(true)
    val isAffordableLoading: StateFlow<Boolean> = _isAffordableLoading.asStateFlow()

    private val _isLoadingSearch = MutableStateFlow(false)
    val isLoadingSearch: StateFlow<Boolean> = _isLoadingSearch.asStateFlow()

    init {
        getDestinations()
        getDestinations(true)
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GoToItineraryDetails -> {
                    _uiEvent.emit(HomeEvent.GoToItineraryDetails(event.data))
                }

                is HomeEvent.SearchItinerary -> {
                    val destination = event.destination
                    val days = event.days
                    if (destination.isNotBlank() && (days.isNotBlank() && days.isDigitsOnly())) {
                        _isLoadingSearch.value = true
                        getItinerary(destination, days.toInt(), true)
                    } else {
                        _uiEvent.emit(HomeEvent.Error("Please enter a valid destination and duration"))
                    }
                }

                else -> {
                    // Do nothing
                }
            }
        }
    }

    private fun getItinerary(
        destination: String,
        duration: Int = 2,
        fromSearch: Boolean = false,
        isAffordable: Boolean = false
    ) {
        viewModelScope.launch {
            if (!fromSearch) {
                if (isAffordable) _isAffordableLoading.value = true else _isPopularLoading.value =
                    true
            }

            val request = ItineraryRequest(destination, duration)

            getItinerariesUseCase(request)
                .collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            if (fromSearch) {
                                _isLoadingSearch.value = true
                            }
                        }

                        is Result.Success -> {
                            result.data?.let { itineraryResponse ->
                                itineraryResponse.toLocationItinerary()?.let { locationItinerary ->
                                    if (!fromSearch) {
                                        if (isAffordable) {
                                            _affordableLocationItineraries.value += locationItinerary
                                        } else {
                                            _locationItineraries.value += locationItinerary
                                        }
                                    } else {
                                        _isLoadingSearch.value = false
                                        _uiEvent.emit(
                                            HomeEvent.GoToItineraryDetails(
                                                locationItinerary
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        is Result.Error -> {
                            when (result.exception) {
                                is ItineraryFetchException -> {
                                    if (fromSearch) {
                                        _isLoadingSearch.value = false
                                    }
                                    _uiEvent.emit(
                                        HomeEvent.Error(
                                            result.exception.message ?: "An error occurred"
                                        )
                                    )
                                }

                                is DestinationFetchException -> {
                                    _uiEvent.emit(
                                        HomeEvent.Error(
                                            result.exception.message ?: "An error occurred"
                                        )
                                    )
                                }
                            }
                        }
                    }

                    if (!fromSearch) {
                        if (isAffordable) _isAffordableLoading.value =
                            false else _isPopularLoading.value = false
                    }
                }
        }
    }


    private fun getDestinations(isAffordable: Boolean = false) {
        viewModelScope.launch {
            fetchDestinationsUseCase(DestinationRequest(isAffordable = isAffordable))
                .collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            _isAffordableLoading.value = true
                        }

                        is Result.Success -> {
                            result.data?.items?.popularDestinations?.forEach { destination ->
                                getItinerary(destination = destination, isAffordable = isAffordable)
                            }
                        }

                        is Result.Error -> {
                            _uiEvent.emit(
                                HomeEvent.Error(
                                    result.exception.message ?: "Error fetching destinations"
                                )
                            )
                            _isAffordableLoading.value = false
                        }
                    }
                }
        }
    }

}