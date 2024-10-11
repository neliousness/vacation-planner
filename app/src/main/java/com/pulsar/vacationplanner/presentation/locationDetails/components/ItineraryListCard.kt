package com.pulsar.vacationplanner.presentation.locationDetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData
import com.pulsar.vacationplanner.presentation.common.components.Title
import com.pulsar.vacationplanner.presentation.locationDetails.LocationDetailsEvent
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun ItineraryListCard(itinerary: List<ItineraryData>, onEvent: (LocationDetailsEvent) -> Unit) {
    Box(modifier = Modifier.padding(top = 10.dp)) {
        Title(title = "Your itinerary", fontSize = 24.sp)
    }
        LazyColumn {
            items(itinerary) { item ->
                ItineraryCard(data = item, onEvent = onEvent)
            }
        }
}


@Preview(showBackground = true)
@Composable
fun ItineraryListCardPreview() {

    val data = locationItinerarys[0].itinerary
    Column {
        Title(title = "Itinerary")
        LazyColumn {
            items(data) { item ->
                ItineraryCard(data = item, onEvent = {})
            }
        }
    }

}