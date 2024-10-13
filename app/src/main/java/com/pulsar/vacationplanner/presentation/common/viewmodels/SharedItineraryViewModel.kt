package com.pulsar.vacationplanner.presentation.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import com.pulsar.vacationplanner.domain.usecases.LoadOnboardDataUseCase
import com.pulsar.vacationplanner.domain.usecases.SaveOnboardDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedLocationItineraryViewModel(
    private val saveOnboardDataUseCase: SaveOnboardDataUseCase,
    private val loadOnboardDataUseCase: LoadOnboardDataUseCase
) :
    ViewModel() {

    private val _selectedLocationItinerary = MutableStateFlow<LocationItinerary?>(null)
    val selectedLocationItinerary: StateFlow<LocationItinerary?> =
        _selectedLocationItinerary.asStateFlow()

    private val _selectedItinerary = MutableStateFlow<ItineraryData?>(null)
    val selectedItinerary: StateFlow<ItineraryData?> =
        _selectedItinerary.asStateFlow()

    fun setSelectedLocationItinerary(locationItinerary: LocationItinerary?) {
        _selectedLocationItinerary.value = locationItinerary
    }

    fun setSelectedItinerary(itineraryData: ItineraryData?) {
        _selectedItinerary.value = itineraryData
    }

    private val _isOnboarded = MutableStateFlow(false)
    val isOnboarded: StateFlow<Boolean> = _isOnboarded

    init {
        loadData()
    }

    fun saveData(key: String, value: Boolean) {
        viewModelScope.launch {
            saveOnboardDataUseCase(key, value)
            _isOnboarded.value = value
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _isOnboarded.value = loadOnboardDataUseCase.invoke()
        }
    }
}