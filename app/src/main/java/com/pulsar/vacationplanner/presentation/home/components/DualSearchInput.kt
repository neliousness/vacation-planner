package com.pulsar.vacationplanner.presentation.home.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pulsar.vacationplanner.R
import com.pulsar.vacationplanner.presentation.home.HomeEvent
import com.pulsar.vacationplanner.ui.theme.MiltaryGreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun DualInputField(
    onEvent: (HomeEvent) -> Unit,
    textPlaceholderLabel: String = "Destination", numberPlaceholderLabel:
    String = "Days"
) {
    var textValue by remember { mutableStateOf("") }
    var numberValue by remember { mutableStateOf("") }

    val textFocusRequester = remember { FocusRequester() }
    val numberFocusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f)
                .focusRequester(textFocusRequester),
            value = textValue,
            onValueChange = {
                textValue = it
            },
            placeholder = {
                Text(
                    text = textPlaceholderLabel,
                    fontSize = 18.sp,
                    textAlign = TextAlign.End
                )
            },
            textStyle = TextStyle.Default.copy(fontSize = 18.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                textFocusRequester.freeFocus()
                numberFocusRequester.requestFocus()
            }) {
            },
            shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MiltaryGreen,
                focusedBorderColor = MiltaryGreen.copy(alpha = 0.5f)
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f)
                .focusRequester(numberFocusRequester),
            value = numberValue,
            onValueChange = { number ->
                numberValue = number.filter { it.isDigit() }
            },
            placeholder = {
                Text(
                    text = numberPlaceholderLabel,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }, textStyle = TextStyle.Default.copy(fontSize = 18.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions {
                numberFocusRequester.freeFocus()
                onEvent(HomeEvent.SearchItinerary(textValue, numberValue))
            },
            shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MiltaryGreen,
                focusedBorderColor = MiltaryGreen.copy(alpha = 0.5f)
            )
        )
        Spacer(modifier = Modifier.size(10.dp))
        Image(
            modifier = Modifier
                .size(36.dp)
                .clickable {
                    Log.d("DualInputField", "Search button clicked --> ${textValue} ${numberValue}")
                    onEvent(HomeEvent.SearchItinerary(textValue, numberValue))
                },
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "Back Arrow"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DualInputFieldPreview() {
    DualInputField(koinViewModel())
}