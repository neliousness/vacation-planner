package com.pulsar.vacationplanner.domain.model.itinerary

data class Activity(
    val description: String,
    val location: Location,
    val meal: String,
    val name: String
)