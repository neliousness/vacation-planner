package com.pulsar.vacationplanner.presentation.locationDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData
import com.pulsar.vacationplanner.presentation.locationDetails.LocationDetailsEvent
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen
import com.pulsar.vacationplanner.util.Constants.DATE_TIME_PATTERN
import com.pulsar.vacationplanner.util.DummyData.locationItinerarys
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.Locale

@Composable
fun ItineraryCard(data: ItineraryData, onEvent: (LocationDetailsEvent) -> Unit) {


    val formatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN)
        .withZoneUTC()

    val dateTime = remember(data.date) {
        try {
            formatter.parseDateTime(data.date)
        } catch (e: IllegalArgumentException) {
            DateTime.now()
        }
    }

    val dayOfMonth = dateTime.dayOfMonth()
        .get()
        .toString()
    val month = dateTime.monthOfYear()
        .getAsShortText(Locale.getDefault())
    val year = dateTime.year()
        .get()
        .toString()



    Card(
        modifier = Modifier
            .padding(bottom = 8.dp, top = 8.dp)
            .height(100.dp)
            .fillMaxWidth()
            .clickable {
                onEvent(LocationDetailsEvent.SelectedItinerary(data))
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MiltaryGreen,
                        shape = RoundedCornerShape(bottomStart = 8.dp, topStart = 8.dp)
                    )
                    .background(color = MiltaryGreen)
                    .height(100.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = dayOfMonth,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)     // Add bottom padding
                    )
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(8.dp)
                    ) {
                        Text(
                            text = month,
                            color = Color.White.copy(alpha = 0.5f),
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp

                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = year,
                            color = Color.White.copy(alpha = 0.5f),
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )

                    }
                }

            }
            Text(
                text = data.title,
                color = MiltaryGreen,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}


@Preview
@Composable
fun ItineraryCardPreview() {

    ItineraryCard(locationItinerarys[0].itinerary[0], onEvent = {})
}