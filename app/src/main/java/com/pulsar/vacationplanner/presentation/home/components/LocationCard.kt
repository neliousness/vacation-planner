package com.pulsar.vacationplanner.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary
import com.pulsar.vacationplanner.presentation.home.HomeEvent
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun LocationCard(data: LocationItinerary, onEvent:(HomeEvent) -> Unit) {
    Card (modifier = Modifier.padding(8.dp).clickable {
        onEvent(HomeEvent.GoToItineraryDetails(data))
    }, elevation = CardDefaults.cardElevation(4.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                .width(LocalConfiguration.current.screenWidthDp.dp / 3)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.page_1),
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            Text(
                text = data.location,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart) // Align to bottom center
                    .padding(bottom = 8.dp)     // Add bottom padding
            )
        }
    }
}


@Preview
@Composable
fun LocationCardPreview() {
    val data = locationItinerarys[0]

    Card {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                .width(LocalConfiguration.current.screenWidthDp.dp / 4)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.page_1),
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            Text(
                text = data.location,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart) // Align to bottom center
                    .padding(bottom = 8.dp)     // Add bottom padding
            )
        }
    }

}