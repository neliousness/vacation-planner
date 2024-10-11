package com.pulsar.vacationplanner.data.repository

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationResponse
import com.pulsar.vacationplanner.data.remote.LocationItineraryApiService
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import com.pulsar.vacationplanner.util.Constants.DESTINATION_RETRY_ATTEMPTS
import com.pulsar.vacationplanner.util.Constants.ITINERARY_RETRY_ATTEMPTS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ItineraryRepositoryImpl(private val apiService: LocationItineraryApiService) :
    ItineraryRepository {

    /**
     * This method fetches itineraties from an AI API and emits the result as a flow. However, given the
     * Inaccuracies presented by the selected model. occasionally the result may become corrupt. therefore this method attempts to fectch
     */
    override fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>> =
        flow {
            var retryCount = 0
            var itineraryResponse: ItineraryResponse? = null

            while (retryCount < ITINERARY_RETRY_ATTEMPTS && itineraryResponse == null) {
                try {
                    val response = apiService.getLocationItinerary(request)
                    if (response.isSuccessful && response.body() != null) {
                        itineraryResponse = response.body()!!
                        emit(Result.success(itineraryResponse))
                    } else {
                        emit(Result.failure(Exception("API request failed with code ${response.code()}")))
                    }
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
                retryCount++
                if (itineraryResponse == null) {
                    delay(1000) // Delay before retrying
                }
            }
            if (itineraryResponse == null) {
                emit(Result.failure(Exception("Failed to fetch itinerary after 5 attempts")))
            }
        }.catch { e ->
            emit(Result.failure(e))
        }

    override fun getDestinations(request: DestinationRequest): Flow<Result<DestinationResponse?>> =
        flow {
            var retryCount = 0
            var popularList: DestinationResponse? = null

            while (retryCount < DESTINATION_RETRY_ATTEMPTS && popularList == null) {
                try {
                    val response = apiService.getDestinations(request)
                    if (response.isSuccessful && response.body() != null) {
                        popularList = response.body()!!
                        emit(Result.success(popularList))
                    } else {
                        emit(Result.failure(Exception("API request failed with code ${response.code()}")))
                    }
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
                retryCount++
                if (popularList == null) {
                    delay(1000) // Delay before retrying
                }
            }
            if (popularList == null) {
                emit(Result.failure(Exception("Failed to fetch itinerary after 5 attempts")))
            }
        }.catch { e ->
            emit(Result.failure(e))
        }
}