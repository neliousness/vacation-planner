package com.pulsar.vacationplanner.presentation.navgraph

sealed class Route(open val route: String) {

    data object OnBoardingScreen : Route(route = "onBoardingScreen")
    data object HomeScreen : Route(route = "homeScreen")
    data object LocationDetailsScreen : Route(route = "locationDetailsScreen")
    data object ItineraryDetailsScreen : Route(route = "itineraryDetailsScreen")
}