package com.pulsar.vacationplanner.presentation.home

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationRequest
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import com.pulsar.vacationplanner.util.extensions.toLocationItinerary
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ItineraryRepository) : ViewModel() {

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
                is HomeEvent.Error -> _uiEvent.emit(HomeEvent.Error("An error occurred"))
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
                if (isAffordable) {
                    _isAffordableLoading.value = true
                } else {
                    _isPopularLoading.value = true
                }
            }
            val request = ItineraryRequest(destination, duration)

            repository.getLocationItinerary(request)
                .catch { e ->
                    _uiEvent.emit(HomeEvent.Error(e.message ?: "An error occurred"))
                    _isAffordableLoading.value = false
                }
                .collect { result ->
                    if (result.isSuccess) {
                        result.getOrNull()
                            ?.let {
                                try {
                                    val locationItinerary = it.toLocationItinerary()
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
                                } catch (e: Exception) {
                                    _isLoadingSearch.value = false
                                    Log.e(
                                        "HomeViewModel",
                                        "${e.message}"
                                    )
                                }
                            }
                    } else {
                        Log.e(
                            "HomeViewModel",
                            "Error fetching itinerary: ${result.exceptionOrNull()?.message}"
                        )
                    }
                    if (!fromSearch) {
                        if (isAffordable) {
                            _isAffordableLoading.value = false
                        } else {
                            _isPopularLoading.value = false
                        }

                    }
                }

        }
    }

    private fun getDestinations(isAffordable: Boolean = false) {
        viewModelScope.launch {
            _isAffordableLoading.value = true
            repository.getDestinations(request = DestinationRequest(isAffordable = isAffordable))
                .catch { e ->
                    _uiEvent.emit(HomeEvent.Error(e.message ?: "An error occurred"))
                    _isAffordableLoading.value = false
                }
                .collect { result ->
                    if (result.isSuccess) {
                        result.getOrNull()
                            ?.let { destinations ->
                                try {
                                    destinations.items.popularDestinations.forEach { destination ->
                                        getItinerary(
                                            destination = destination,
                                            isAffordable = isAffordable
                                        )
                                    }
                                } catch (e: Exception) {
                                    Log.e(
                                        "HomeViewModel",
                                        "${e.message}"
                                    )
                                }
                            }
                    } else {
                        Log.e(
                            "HomeViewModel",
                            "Error fetching itinerary: ${result.exceptionOrNull()?.message}"
                        )
                    }
                    _isAffordableLoading.value = false
                }

        }
    }
}