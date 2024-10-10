package com.pulsar.vacationplanner.presentation.onboarding

import androidx.annotation.DrawableRes
import com.pulsar.vacationplanner.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Welcome to Vacation Planner!",
        description = "Plan your dream vacation with ease using our AI-powered itinerary generator.",
        image = R.drawable.page_1 // Replace with relevant image
    ),
    Page(
        title = "Personalized Itineraries",
        description = "Tell us your destination and trip duration, and we'll create a custom itinerary just for you.",
        image = R.drawable.page_1 // Replace with relevant image
    ),
    Page(
        title = "Explore and Enjoy!",
        description = "Discover amazing attractions, restaurants, and activities tailored to your preferences.",
        image = R.drawable.page_1 // Replace with relevant image
    )
)