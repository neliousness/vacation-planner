package com.pulsar.vacationplanner.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.presentation.common.components.FullScreenLoader
import com.pulsar.vacationplanner.presentation.common.components.Header
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.home.components.DualInputField
import com.pulsar.vacationplanner.presentation.home.components.LocationListCard
import com.pulsar.vacationplanner.presentation.navgraph.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    sharedLocationItineraryViewModel: SharedLocationItineraryViewModel,
    navHostController: NavHostController
) {

    val popularLocationItineraries by homeViewModel.locationItineraries.collectAsState()
    val affordableLocation by homeViewModel.affordableLocationItineraries.collectAsState()
    val isPopularLoading by homeViewModel.isPopularLoading.collectAsState()
    val isAffordableLoading by homeViewModel.isAffordableLoading.collectAsState()
    val isLoadingSearch by homeViewModel.isLoadingSearch.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        homeViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeEvent.GoToItineraryDetails -> {
                    sharedLocationItineraryViewModel.setSelectedLocationItinerary(event.data)
                    navHostController.navigate(Route.LocationDetailsScreen.route)
                }

                is HomeEvent.Error -> {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                }

                else -> {
                    // Do nothing
                }
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Header()
                Spacer(modifier = Modifier.size(60.dp))
                DualInputField(homeViewModel::onEvent)
            }
            Spacer(modifier = Modifier.size(30.dp))
            LocationListCard(
                "Popular Destinations",
                isPopularLoading,
                popularLocationItineraries,
                onEvent = homeViewModel::onEvent
            )
            Spacer(modifier = Modifier.size(30.dp))
            LocationListCard(
                "Affordable Destinations",
                isAffordableLoading,
                affordableLocation, onEvent = homeViewModel::onEvent
            )

        }
        if (isLoadingSearch) {
            FullScreenLoader()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(koinViewModel(), koinViewModel(), rememberNavController())
}