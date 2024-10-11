package com.pulsar.vacationplanner.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.presentation.common.components.Header
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.details.components.ItineraryListCard
import com.pulsar.vacationplanner.presentation.details.components.OSMapView
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    sharedLocationItineraryViewModel: SharedLocationItineraryViewModel,
    navHostController: NavHostController
) {
    //region observables
    val locationItinerary by sharedLocationItineraryViewModel.selectedLocationItinerary
        .collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is DetailsEvent.GoBack -> navHostController.popBackStack()
            }
        }
    }

    //endregion observables

    locationItinerary?.let { itinerary ->
        // Use the itinerary data in your UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, start = 24.dp, end = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        viewModel.onEvent(DetailsEvent.GoBack)
                    }, verticalAlignment = Alignment
                    .CenterVertically, horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Image(
                    modifier = Modifier.height(32.dp),
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "Back Arrow"
                )
            }
            Card {
                OSMapView(
                    context = LocalContext.current,
                    latitude = 37.7749,   // Example: San Francisco
                    longitude = -122.4194,
                    zoomLevel = 12.0
                )
            }


            //Location Title
            Header(title = itinerary.location)
            ItineraryListCard(itinerary.itinerary)

        }
    }

}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(koinViewModel(), koinViewModel(), rememberNavController())
}
