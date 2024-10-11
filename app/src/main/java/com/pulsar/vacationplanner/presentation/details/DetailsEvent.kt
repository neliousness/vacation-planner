package com.pulsar.vacationplanner.presentation.details

import com.pulsar.vacationplanner.domain.model.LocationItinerary

sealed class DetailsEvent {

    data object GoBack : DetailsEvent()
}