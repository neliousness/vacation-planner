package com.pulsar.vacationplanner.data.repository

import com.pulsar.vacationplanner.data.model.ItineraryRequest
import com.pulsar.vacationplanner.data.model.ItineraryResponse
import com.pulsar.vacationplanner.data.remote.LocationItineraryApiService
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ItineraryRepositoryImpl(private val apiService: LocationItineraryApiService) :
    ItineraryRepository {
    override fun getLocationItinerary(request: ItineraryRequest): Flow<Result<ItineraryResponse?>> =
        flow {
            var retryCount = 0
            var itineraryResponse: ItineraryResponse? = null

            while (retryCount < 5 && itineraryResponse == null) {
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

    override fun getPopularDestinations(): Flow<Result<List<String>?>> =
        flow {
            var retryCount = 0
            var popularList: List<String>? = null

            while (retryCount < 5 && popularList == null) {
                try {
                    val response = apiService.getPopularDestinations()
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