package com.pulsar.vacationplanner.presentation.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pulsar.vacationplanner.domain.model.ItineraryData
import com.pulsar.vacationplanner.presentation.home.HomeEvent
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun ItineraryCard(data: ItineraryData, onEvent: (HomeEvent) -> Unit) {


    Card(modifier = Modifier
        .padding(8.dp)
        .clickable {
            //onEvent(HomeEvent.ItineraryDetails(data))
        },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MiltaryGreen.copy(alpha = 0.5f))
    ) {
        Row() {
            Text(
                text = data.date,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 8.dp)     // Add bottom padding
            )
            Text(
                text = data.title,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 8.dp)     // Add bottom padding
            )
        }
    }
}


@Preview
@Composable
fun ItineraryCardPreview() {

    ItineraryCard(locationItinerarys[0].itinerary[0], onEvent = {})
}