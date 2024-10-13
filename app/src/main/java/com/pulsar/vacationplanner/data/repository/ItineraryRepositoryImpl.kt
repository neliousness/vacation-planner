package com.pulsar.vacationplanner.data.repository

import android.util.Log
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.destinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.destinations.DestinationResponse
import com.pulsar.vacationplanner.data.remote.LocationItineraryApi
import com.pulsar.vacationplanner.domain.exceptions.ItineraryFetchException
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import com.pulsar.vacationplanner.util.Constants.DESTINATION_RETRY_ATTEMPTS
import com.pulsar.vacationplanner.util.Constants.ITINERARY_RETRY_ATTEMPTS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import com.pulsar.vacationplanner.domain.common.Result
import com.pulsar.vacationplanner.domain.exceptions.DestinationFetchException

class ItineraryRepositoryImpl(private val apiService: LocationItineraryApi) :
    ItineraryRepository {

    /**
     * This method fetches itineraties from an AI API and emits the result as a flow. However, given the
     * Inaccuracies presented by the selected model. occasionally the result may become corrupt. therefore this method attempts to fetch
     */
    override fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>> =
        flow {
            var retryCount = 0
            var itineraryResponse: ItineraryResponse? = null

            while (retryCount < ITINERARY_RETRY_ATTEMPTS && itineraryResponse == null) {
                try {
                    val response = apiService.getLocationItinerary(request)
                    if (response.isSuccessful && response.body() != null) {
                        itineraryResponse = response.body()
                        emit(Result.Success(itineraryResponse))
                    } else {
                        Log.d(this.javaClass.simpleName, "API request failed with code ${response.code()}")
                        emit(Result.Error(Exception("API request failed with code ${response.code()}")))
                    }
                } catch (e: Exception) {
                    Log.e(this.javaClass.name, "${e.message}")
                    emit(Result.Error(e))
                }
                retryCount++
                if (itineraryResponse == null) {
                    delay(1000) // Delay before retrying
                }
            }
            if (itineraryResponse == null) {
                emit(Result.Error(ItineraryFetchException("Failed to fetch itinerary for ${request.destination}")))
            }
        }.catch { e ->
            emit(Result.Error(e))
        }

    override fun getDestinations(request: DestinationRequest): Flow<Result<DestinationResponse?>> =
        flow {
            var retryCount = 0
            var popularList: DestinationResponse? = null

            while (retryCount < DESTINATION_RETRY_ATTEMPTS && popularList == null) {
                try {
                    val response = apiService.getDestinations(request)
                    if (response.isSuccessful && response.body() != null) {
                        popularList = response.body()
                        emit(Result.Success(popularList))
                    } else {
                        emit(Result.Error(Exception("API request failed with code ${response.code()}")))
                    }
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
                retryCount++
                if (popularList == null) {
                    delay(1000) // Delay before retrying
                }
            }
            if (popularList == null) {
                val destinationType = if (request.isAffordable) "affordable" else "popular"
                emit(Result.Error(DestinationFetchException("Failed to fetch $destinationType destinations")))
            }
        }.catch { e ->
            emit(Result.Error(e))
        }
}