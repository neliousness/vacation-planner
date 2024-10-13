package com.pulsar.vacationplanner.domain.repository

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.destinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.destinations.DestinationResponse
import kotlinx.coroutines.flow.Flow
import com.pulsar.vacationplanner.domain.common.Result

interface ItineraryRepository {
    fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>>

    fun getDestinations(request: DestinationRequest): Flow<Result<DestinationResponse?>>
}