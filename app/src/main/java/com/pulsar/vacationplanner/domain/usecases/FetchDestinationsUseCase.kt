package com.pulsar.vacationplanner.domain.usecases

import com.pulsar.vacationplanner.data.model.destinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.destinations.DestinationResponse
import com.pulsar.vacationplanner.domain.common.Result
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import kotlinx.coroutines.flow.Flow

class FetchDestinationsUseCase(private val repository: ItineraryRepository) {

    operator fun invoke(request: DestinationRequest): Flow<Result<DestinationResponse?>> {
        return repository.getDestinations(request)
    }
}