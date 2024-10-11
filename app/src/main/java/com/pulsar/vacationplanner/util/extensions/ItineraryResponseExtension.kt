package com.pulsar.vacationplanner.util.extensions

import com.pulsar.vacationplanner.data.model.ItineraryResponse
import com.pulsar.vacationplanner.domain.model.LocationItinerary

fun ItineraryResponse.toLocationItinerary(): LocationItinerary {
    return LocationItinerary(
        location = this.items.location,
        itinerary = this.items.itinerary
    )
}