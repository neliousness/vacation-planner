package com.pulsar.vacationplanner.presentation.locationDetails

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.presentation.common.components.Header
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.locationDetails.components.ItineraryListCard
import com.pulsar.vacationplanner.presentation.locationDetails.components.OSMapView
import com.pulsar.vacationplanner.presentation.navgraph.Route
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationDetailsScreen(
    viewModel: LocationDetailsViewModel,
    sharedLocationItineraryViewModel: SharedLocationItineraryViewModel,
    navHostController: NavHostController
) {
    //region observables
    val locationItinerary by sharedLocationItineraryViewModel.selectedLocationItinerary
        .collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is LocationDetailsEvent.GoBack -> navHostController.popBackStack()
                is LocationDetailsEvent.SelectedItinerary -> {
                    sharedLocationItineraryViewModel
                        .setSelectedItinerary(event.itineraryData)
                    navHostController.navigate(Route.ItineraryDetailsScreen.route)
                }
            }
        }
    }
    //endregion observables

    locationItinerary?.let { itinerary ->

        Log.d("LocationDetailsScreen", "LocationDetailsScreen: $itinerary")
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
                        viewModel.onEvent(LocationDetailsEvent.GoBack)
                    }, verticalAlignment = Alignment
                    .CenterVertically, horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Image(
                    modifier = Modifier.height(28.dp),
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "Back Arrow"
                )
                Spacer(modifier = Modifier.size(10.dp))
                Header(title = itinerary.location, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.size(5.dp))
            Card(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth(), border = BorderStroke(
                    2.dp, MiltaryGreen
                )
            ) {
                OSMapView(
                    context = LocalContext.current,
                    latitude = itinerary.country?.latitude ?: 0.0,
                    longitude = itinerary.country?.longitude ?: 0.0,
                    zoomLevel = 15.0
                )
            }
            ItineraryListCard(itinerary.itinerary, viewModel::onEvent)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LocationDetailsScreenPreview() {
    LocationDetailsScreen(koinViewModel(), koinViewModel(), rememberNavController())
}
