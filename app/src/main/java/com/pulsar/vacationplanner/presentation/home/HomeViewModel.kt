package com.pulsar.vacationplanner.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<HomeEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: HomeEvent) {
        viewModelScope.launch {
            when(event) {
                is HomeEvent.ItineraryDetails -> {
                    _uiEvent.emit(HomeEvent.ItineraryDetails(event.data))
                }

                is HomeEvent.SearchItinerary -> TODO()
            }
        }
    }
}