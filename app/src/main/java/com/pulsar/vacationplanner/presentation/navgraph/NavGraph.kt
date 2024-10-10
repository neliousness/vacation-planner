package com.pulsar.vacationplanner.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.presentation.home.HomeScreen
import com.pulsar.vacationplanner.presentation.onboarding.OnboardingScreen
import com.pulsar.vacationplanner.presentation.onboarding.OnboardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    startDestination: String = Route.OnBoardingScreen.route
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.OnBoardingScreen.route) {
            val viewModel : OnboardingViewModel = koinViewModel()
            OnboardingScreen(onEvent = viewModel::onEvent, navController)
        }

        composable(route = Route.HomeScreen.route) {
            HomeScreen()
        }
    }
}