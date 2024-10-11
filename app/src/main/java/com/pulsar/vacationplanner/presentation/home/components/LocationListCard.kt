package com.pulsar.vacationplanner.presentation.home.components

import ShimmerLocationCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import com.pulsar.vacationplanner.presentation.common.components.Title
import com.pulsar.vacationplanner.presentation.home.HomeEvent
import com.pulsar.vacationplanner.util.DummyData.locationItinerarys

@Composable
fun LocationListCard(
    title: String,
    isLoading: Boolean = false,
    itineraries: List<LocationItinerary>,
    onEvent: (HomeEvent) -> Unit
) {
    Column {
        Box(modifier = Modifier.padding(horizontal = 10.dp)) {
            Title(title = title)
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(20) {
                    ShimmerLocationCard()
                }
            }
        } else {
            // Display actual location itinerary cards
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(itineraries) { itinerary ->
                    LocationCard(data = itinerary, onEvent = onEvent)
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LocationListCardPreview() = LocationListCard(
    "Suggestions",
    false,
    locationItinerarys,
    {})


