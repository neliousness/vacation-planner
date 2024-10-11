package com.pulsar.vacationplanner.presentation.locationDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LocationDetailsViewModel : ViewModel() {
    private val _uiEvent = MutableSharedFlow<LocationDetailsEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: LocationDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is LocationDetailsEvent.GoBack -> {
                    _uiEvent.emit(LocationDetailsEvent.GoBack)
                }

                is LocationDetailsEvent.SelectedItinerary ->  _uiEvent.emit(LocationDetailsEvent
                    .SelectedItinerary(event.itineraryData))
            }
        }
    }
}