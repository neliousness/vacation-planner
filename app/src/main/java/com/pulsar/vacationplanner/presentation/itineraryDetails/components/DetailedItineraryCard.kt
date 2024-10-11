package com.pulsar.vacationplanner.presentation.itineraryDetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.domain.model.Activity
import com.pulsar.vacationplanner.presentation.home.HomeEvent
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen
import com.pulsar.vacationplanner.util.Constants.locationItinerarys

@Composable
fun DetailedItineraryCard(data: Activity, onEvent: (HomeEvent) -> Unit) {

    Card(
        modifier = Modifier
            .padding(bottom = 8.dp, top = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MiltaryGreen,
                        shape = RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp)
                    )
                    .background(color = MiltaryGreen)
                    .wrapContentHeight()
            ) {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = data.name,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 8.dp, bottom = 8.dp)     // Add bottom
                        // padding
                    )
                }

            }
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = "Clock",
                        modifier = Modifier.height(28.dp).padding(start = 8.dp),
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = data.description,
                        color = MiltaryGreen,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(16.dp)
                        // Add bottom padding
                    )
                }
                Row (verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(R.drawable.ic_food),
                        contentDescription = "Clock",
                        modifier = Modifier.height(28.dp).padding(start = 8.dp),
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = data.meal,
                        color = MiltaryGreen,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(16.dp)
                        // Add bottom padding
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DetailedItineraryCardPreview() {

    DetailedItineraryCard(locationItinerarys[0].itinerary[0].activities[0], onEvent = {})
}