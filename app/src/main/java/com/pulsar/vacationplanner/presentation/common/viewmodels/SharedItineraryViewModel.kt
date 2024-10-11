package com.pulsar.vacationplanner.presentation.common.viewmodels

import androidx.lifecycle.ViewModel
import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedLocationItineraryViewModel : ViewModel() {

    private val _selectedLocationItinerary = MutableStateFlow<LocationItinerary?>(null)
    val selectedLocationItinerary: StateFlow<LocationItinerary?> =
        _selectedLocationItinerary.asStateFlow()

    private val _selectedItinerary = MutableStateFlow<ItineraryData?>(null)
    val selectedItinerary: StateFlow<ItineraryData?> =
        _selectedItinerary.asStateFlow()

    fun setSelectedLocationItinerary(locationItinerary: LocationItinerary?) {
        _selectedLocationItinerary.value = locationItinerary
    }

    fun setSelectedItinerary(itineraryData: ItineraryData?)  {
        _selectedItinerary.value = itineraryData
    }
}