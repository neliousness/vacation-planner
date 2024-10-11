package com.pulsar.vacationplanner.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pulsar.vacationplanner.data.model.ItineraryRequest
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

    private val _uiState = MutableStateFlow<HomeState>(HomeState.IsLoading(true))
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _locations = MutableSharedFlow<HomeEvent>()
    val locations = _locations.asSharedFlow()

    init {
        getItinerary("Cape Town", 2)
    }

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeEvent.GoToItineraryDetails -> {
                    _uiEvent.emit(HomeEvent.GoToItineraryDetails(event.data))
                }

                is HomeEvent.SearchItinerary -> TODO()
                is HomeEvent.Error -> TODO()
                is HomeEvent.ItineraryFetchResult -> TODO()
            }
        }
    }

    fun getItinerary(destination: String, duration: Int) {
        viewModelScope.launch {
            _uiState.value = HomeState.IsLoading(true)
            val request = ItineraryRequest(destination, duration)

            repository.getLocationItinerary(request)
                .catch { e ->
                    // Handle exceptions (e.g., emit an error state)
                    _uiEvent.emit(HomeEvent.Error(e.message ?: "An error occurred"))
                }
                .collect { result ->
                    if (result.isSuccess) {
                        result.getOrNull()?.let {
                            val locationItinerary = it.toLocationItinerary()
                            _uiEvent.emit(HomeEvent.ItineraryFetchResult(locationItinerary))
                        }
                    } else {
                        Log.e(
                            "HomeViewModel",
                            "Error fetching itinerary: ${result.exceptionOrNull()?.message}"
                        )
                    }
                }

            _uiState.value = HomeState.IsLoading(false)
        }
    }
}