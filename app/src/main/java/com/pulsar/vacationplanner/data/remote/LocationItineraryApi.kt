package com.pulsar.vacationplanner.data.remote

import com.pulsar.vacationplanner.data.model.itinerary.ItineraryRequest
import com.pulsar.vacationplanner.data.model.itinerary.ItineraryResponse
import com.pulsar.vacationplanner.data.model.destinations.DestinationRequest
import com.pulsar.vacationplanner.data.model.destinations.DestinationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LocationItineraryApi {

    @POST("api/v1/itinerary")
    suspend fun getLocationItinerary(@Body request: ItineraryRequest): Response<ItineraryResponse>

    @POST("api/v1/destinations")
    suspend fun getDestinations(@Body request: DestinationRequest): Response<DestinationResponse>
}