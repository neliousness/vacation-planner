package com.pulsar.vacationplanner.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.presentation.common.components.Header
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen
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
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.45f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = MiltaryGreen,
                        shape = RoundedCornerShape(8.dp)
                    )
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
            }

            //Location Title
            Header(title = itinerary.location)

        }
    }

}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(koinViewModel(), koinViewModel(), rememberNavController())
}
