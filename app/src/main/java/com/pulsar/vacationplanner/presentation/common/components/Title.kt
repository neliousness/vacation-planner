package com.pulsar.vacationplanner.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen


@Composable
fun Title(
    title: String = "Popular Destinations", fontSize: TextUnit = 16.sp, startPadding: Dp = 0.dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp).padding(start = startPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = title,
            color = MiltaryGreen.copy(alpha = 0.75f),
            fontSize = fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    Title()
}