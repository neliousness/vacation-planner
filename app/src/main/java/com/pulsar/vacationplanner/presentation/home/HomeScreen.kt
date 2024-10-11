package com.pulsar.vacationplanner.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.presentation.common.components.Header
import com.pulsar.vacationplanner.presentation.common.components.InputField
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.home.components.LocationListCard
import com.pulsar.vacationplanner.presentation.navgraph.Route
import com.pulsar.vacationplanner.util.Constants.locationItinerarys
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    sharedLocationItineraryViewModel: SharedLocationItineraryViewModel,
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = Unit) {
        homeViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is HomeEvent.GoToItineraryDetails -> {
                    sharedLocationItineraryViewModel.setSelectedLocationItinerary(event.data)
                    navHostController.navigate(
                        Route.DetailsScreen
                            .route
                    )
                }

                is HomeEvent.SearchItinerary -> TODO()
                is HomeEvent.Error -> TODO()
            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 24.dp, end = 24.dp)
    ) {
        Header()
        Spacer(modifier = Modifier.size(60.dp))
        InputField(onTextChanged = {}, "Search")
        Spacer(modifier = Modifier.size(30.dp))
        LocationListCard(
            "Popular Destinations",
            locationItinerarys,
            onEvent = homeViewModel::onEvent
        )
        Spacer(modifier = Modifier.size(30.dp))
        LocationListCard("Suggestions", locationItinerarys, onEvent = homeViewModel::onEvent)

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(koinViewModel(), koinViewModel(), rememberNavController())
}