package com.pulsar.vacationplanner.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pulsar.vacationplanner.domain.model.LocationItinerary
import com.pulsar.vacationplanner.presentation.common.components.Title
import com.pulsar.vacationplanner.presentation.home.HomeEvent
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun LocationListCard(title : String, itineraries: List<LocationItinerary>, onEvent: (HomeEvent) ->
Unit) {
    Column {
        Title(title = title)
        LazyRow {
            items(itineraries) { item ->
                LocationCard(data = item, onEvent = onEvent)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LocationListCardPreview() = LocationListCard("Suggestions",locationItinerarys, {})