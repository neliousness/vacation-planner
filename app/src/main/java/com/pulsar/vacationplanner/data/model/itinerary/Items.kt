package com.pulsar.vacationplanner.data.model.itinerary

import com.pulsar.vacationplanner.domain.model.itinerary.Country
import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData

data class Items(
    val itinerary: List<ItineraryData>,
    val location: String,
    val country : Country? = null,
)