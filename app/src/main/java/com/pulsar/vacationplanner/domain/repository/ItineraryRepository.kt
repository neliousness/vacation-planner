package com.pulsar.vacationplanner.domain.repository

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationResponse
import kotlinx.coroutines.flow.Flow

interface ItineraryRepository {
    fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>>

    fun getDestinations(request: DestinationRequest): Flow<Result<DestinationResponse?>>
}