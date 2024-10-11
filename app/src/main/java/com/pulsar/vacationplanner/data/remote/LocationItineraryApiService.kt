package com.pulsar.vacationplanner.data.remote

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.popularDestinations.DestinationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LocationItineraryApiService {

    @POST("api/v1/itinerary")
    suspend fun getLocationItinerary(@Body request: ItineraryRequest): Response<ItineraryResponse>

    @POST("api/v1/destinations")
    suspend fun getDestinations(@Body request: DestinationRequest): Response<DestinationResponse>
}