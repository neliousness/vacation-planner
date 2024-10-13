package com.pulsar.vacationplanner.domain.usecases

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.domain.common.Result
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import kotlinx.coroutines.flow.Flow

class SearchItineraryUseCase(private val repository: ItineraryRepository) {
    operator fun invoke(request: ItineraryRequest): Flow<Result<ItineraryResponse?>> {
        return repository.getLocationItinerary(request)
    }
}