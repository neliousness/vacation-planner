package com.pulsar.vacationplanner.presentation.itineraryDetails.components

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
import com.pulsar.vacationplanner.domain.model.itinerary.Activity
import com.pulsar.vacationplanner.presentation.common.components.Title
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun DetailedItineraryListCard(itinerary: List<Activity>) {
    Box(modifier = Modifier.padding(top = 10.dp)) {
        Title(title = "What you'll be doing", fontSize = 24.sp)
    }
        LazyColumn {
            items(itinerary) { item ->
                DetailedItineraryCard(data = item, onEvent = {})
            }
        }
}


@Preview(showBackground = true)
@Composable
fun DetailedItineraryListCardPreview() {

    val data = locationItinerarys[0].itinerary[0].activities
    Column {
        Title(title = "Itinerary")
        LazyColumn {
            items(data) { item ->
                DetailedItineraryCard(data = item, onEvent = {})
            }
        }
    }

}