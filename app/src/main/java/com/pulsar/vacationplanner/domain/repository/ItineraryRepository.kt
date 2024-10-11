package com.pulsar.vacationplanner.domain.repository

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.popularDestinations.PopularDestinationResponse
import kotlinx.coroutines.flow.Flow

interface ItineraryRepository {
     fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>>

     fun getPopularDestinations(): Flow<Result<PopularDestinationResponse?>>
}