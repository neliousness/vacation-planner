package com.pulsar.vacationplanner.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulsar.vacationplanner.domain.model.ItineraryData
import com.pulsar.vacationplanner.presentation.common.components.Title
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun ItineraryListCard(itinerary: List<ItineraryData>) {

    Column {
        Title(title = "Itinerary", fontSize = 24.sp, startPadding = 16.dp)
        LazyColumn {
            items(itinerary) { item ->
                ItineraryCard(data = item, onEvent = {})
            }
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