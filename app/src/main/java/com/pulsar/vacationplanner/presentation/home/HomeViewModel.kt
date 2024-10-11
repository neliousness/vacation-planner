package com.pulsar.vacationplanner.presentation.home

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulsar.vacationplanner.data.model.ItineraryRequest
import com.pulsar.vacationplanner.domain.model.LocationItinerary
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

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getItinerary("Cape Town", 2)
        getItinerary("Maputo", 2)
        getItinerary("Greece", 2)
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
                    if (destination.isNotBlank() && (days.isNotBlank() && days.isDigitsOnly()) ) {
                        getItinerary(destination, days.toInt(), true)
                    } else {
                        _uiEvent.emit(HomeEvent.Error("Please enter a valid destination and duration"))
                    }
                }

                is HomeEvent.Error -> _uiEvent.emit(HomeEvent.Error("An error occurred"))
            }
        }
    }

    private fun getItinerary(destination: String, duration: Int, fromSearch: Boolean = false) {
        viewModelScope.launch {
            if (!fromSearch) {
                _isLoading.value = true
            }
            val request = ItineraryRequest(destination, duration)

            repository.getLocationItinerary(request)
                .catch { e ->
                    _uiEvent.emit(HomeEvent.Error(e.message ?: "An error occurred"))
                    _isLoading.value = false
                }
                .collect { result ->
                    if (result.isSuccess) {
                        result.getOrNull()
                            ?.let {
                                try {
                                    val locationItinerary = it.toLocationItinerary()
                                    if (!fromSearch) {
                                        _locationItineraries.value += locationItinerary
                                    } else {
                                        _uiEvent.emit(
                                            HomeEvent.GoToItineraryDetails(
                                                locationItinerary
                                            )
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
                    if (!fromSearch) {
                        _isLoading.value = false
                    }
                }

        }
    }
}