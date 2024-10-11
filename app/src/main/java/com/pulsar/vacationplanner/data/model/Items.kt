package com.pulsar.vacationplanner.data.model

import com.pulsar.vacationplanner.domain.model.ItineraryData

data class Items(
    val itinerary: List<ItineraryData>,
    val location: String,
)