package com.pulsar.vacationplanner.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val _uiEvent = MutableSharedFlow<DetailsEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: DetailsEvent) {
        viewModelScope.launch {
            when (event) {
                is DetailsEvent.GoBack -> {
                    _uiEvent.emit(DetailsEvent.GoBack)
                }
            }
        }
    }
}