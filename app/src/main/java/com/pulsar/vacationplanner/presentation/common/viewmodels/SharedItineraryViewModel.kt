package com.pulsar.vacationplanner.presentation.common.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulsar.vacationplanner.domain.model.LocationItinerary
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedLocationItineraryViewModel : ViewModel() {

    private val _selectedLocationItinerary = MutableStateFlow<LocationItinerary?>(null)
    val selectedLocationItinerary: StateFlow<LocationItinerary?> = _selectedLocationItinerary.asStateFlow()

    fun setSelectedLocationItinerary(locationItinerary: LocationItinerary?) {
        _selectedLocationItinerary.value = locationItinerary
    }
}