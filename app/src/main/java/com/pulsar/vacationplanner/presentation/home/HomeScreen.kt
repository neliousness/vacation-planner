package com.pulsar.vacationplanner.presentation.home

import android.widget.Toast
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

    val locationItineraries by homeViewModel.locationItineraries.collectAsState()
    val recentLocationItineraries by homeViewModel.locationItineraries.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        homeViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeEvent.GoToItineraryDetails -> {
                    sharedLocationItineraryViewModel.setSelectedLocationItinerary(event.data)
                    navHostController.navigate(Route.LocationDetailsScreen.route)
                }

                is HomeEvent.SearchItinerary -> {}
                is HomeEvent.Error -> {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 24.dp, end = 24.dp)
    ) {
        Header()
        Spacer(modifier = Modifier.size(60.dp))
        DualInputField(homeViewModel::onEvent)
        Spacer(modifier = Modifier.size(30.dp))
        LocationListCard(
            "Popular Destinations",
            isLoading,
            locationItineraries,
            onEvent = homeViewModel::onEvent
        )
        Spacer(modifier = Modifier.size(30.dp))
        LocationListCard(
            "Recent Locations",
            isLoading,
            locationItineraries, onEvent = homeViewModel::onEvent
        )

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(koinViewModel(), koinViewModel(), rememberNavController())
}