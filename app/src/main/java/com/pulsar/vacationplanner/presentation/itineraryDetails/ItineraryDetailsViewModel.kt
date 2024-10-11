package com.pulsar.vacationplanner.presentation.itineraryDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ItineraryDetailsViewModel : ViewModel() {
    private val _uiEvent = MutableSharedFlow<ItineraryDetailsEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: ItineraryDetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is ItineraryDetailsEvent.GoBack -> {
                    _uiEvent.emit(ItineraryDetailsEvent.GoBack)
                }
            }
        }
    }
}