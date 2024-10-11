package com.pulsar.vacationplanner.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(open val route: String, arguments: List<NamedNavArgument> = emptyList()) {

    data object OnBoardingScreen : Route(route = "onBoardingScreen")
    data object HomeScreen : Route(route = "homeScreen")
    data object DetailsScreen : Route(route = "detailsScreen")
}