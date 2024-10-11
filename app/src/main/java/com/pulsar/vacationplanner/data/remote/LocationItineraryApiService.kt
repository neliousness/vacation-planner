package com.pulsar.vacationplanner.data.remote

import com.pulsar.vacationplanner.data.model.ItineraryRequest
import com.pulsar.vacationplanner.data.model.ItineraryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LocationItineraryApiService {

    @POST("api/v1/itinerary")
    suspend fun getLocationItinerary(@Body request: ItineraryRequest): Response<ItineraryResponse>

    @GET("api/v1/popular-destinations")
    suspend fun getPopularDestinations(): Response<List<String>>
}