package com.pulsar.vacationplanner.util.extensions

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary

fun ItineraryResponse.toLocationItinerary(): LocationItinerary {
    return LocationItinerary(
        location = this.items.location,
        itinerary = this.items.itinerary,
        country = this.items.country
    )
}