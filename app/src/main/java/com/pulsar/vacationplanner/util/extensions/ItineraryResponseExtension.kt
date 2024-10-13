package com.pulsar.vacationplanner.util.extensions

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import com.pulsar.vacationplanner.util.Constants.EMPTY

fun ItineraryResponse?.toLocationItinerary(): LocationItinerary? {
    return LocationItinerary(
        location = this?.items?.location ?: EMPTY,
        itinerary = this?.items?.itinerary ?: emptyList(),
        country = this?.items?.country
    )
}