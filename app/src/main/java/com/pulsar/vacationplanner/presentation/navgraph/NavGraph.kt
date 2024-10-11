package com.pulsar.vacationplanner.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.home.HomeScreen
import com.pulsar.vacationplanner.presentation.itineraryDetails.ItineraryDetailsScreen
import com.pulsar.vacationplanner.presentation.locationDetails.LocationDetailsScreen
import com.pulsar.vacationplanner.presentation.onboarding.OnboardingScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val sharedLocationItineraryViewModel: SharedLocationItineraryViewModel = koinViewModel()
    val isOnboarded by sharedLocationItineraryViewModel.isOnboarded.collectAsState()

    val startDestination = if (isOnboarded) Route.HomeScreen.route else Route.OnBoardingScreen.route

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.OnBoardingScreen.route) {
            OnboardingScreen(koinViewModel(), sharedLocationItineraryViewModel, navController)
        }

        composable(route = Route.HomeScreen.route) {
            HomeScreen(koinViewModel(), sharedLocationItineraryViewModel, navController)
        }

        composable(route = Route.LocationDetailsScreen.route) {
            LocationDetailsScreen(koinViewModel(), sharedLocationItineraryViewModel, navController)
        }

        composable(route = Route.ItineraryDetailsScreen.route) {
            ItineraryDetailsScreen(koinViewModel(), sharedLocationItineraryViewModel, navController)
        }
    }
}