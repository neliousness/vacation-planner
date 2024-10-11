package com.pulsar.vacationplanner.domain.repository

import com.pulsar.vacationplanner.data.model.ItineraryRequest
import com.pulsar.vacationplanner.data.model.ItineraryResponse
import kotlinx.coroutines.flow.Flow

interface ItineraryRepository {
     fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>>

     fun getPopularDestinations(): Flow<Result<List<String>?>>
}