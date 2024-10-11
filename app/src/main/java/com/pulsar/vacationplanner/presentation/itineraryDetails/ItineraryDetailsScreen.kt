package com.pulsar.vacationplanner.presentation.itineraryDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.presentation.common.components.Header
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.itineraryDetails.components.DetailedItineraryListCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ItineraryDetailsScreen(
    viewModel: ItineraryDetailsViewModel,
    sharedLocationItineraryViewModel: SharedLocationItineraryViewModel,
    navHostController: NavHostController
) {
    //region observables
    val itineraryData by sharedLocationItineraryViewModel.selectedItinerary
        .collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is ItineraryDetailsEvent.GoBack -> navHostController.popBackStack()
            }
        }
    }
    //endregion observables

    itineraryData?.let { itinerary ->

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
                        viewModel.onEvent(ItineraryDetailsEvent.GoBack)
                    }, verticalAlignment = Alignment
                    .CenterVertically, horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Image(
                    modifier = Modifier.height(28.dp),
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "Back Arrow"
                )
                Spacer(modifier = Modifier.size(10.dp))
                Header(title = itinerary.title, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.size(5.dp))

            DetailedItineraryListCard(itinerary.activities)

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ItineraryDetailsScreenPreview() {
    ItineraryDetailsScreen(koinViewModel(), koinViewModel(), rememberNavController())
}
