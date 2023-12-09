package com.example.composecatalogcomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import java.time.temporal.ValueRange


@Composable
fun BasicSlider(){
    var sliderPosition by rememberSaveable { mutableStateOf(0f) }
    Slider(value = sliderPosition, onValueChange = {sliderPosition = it})
    Text(text = sliderPosition.toString())
}
@Composable
fun AdvanceSlider(){
   Column(horizontalAlignment = Alignment.CenterHorizontally) {
       var sliderPosition by remember { mutableStateOf(0f) }
       Slider(
           value = sliderPosition,
           onValueChange = {sliderPosition = it},
           valueRange = 0f..10f,
           steps = 9
       )
       Text(text = sliderPosition.toString())
   }
}

@Composable
fun MyRangeSlider(){
    var currentRange by remember { mutableStateOf(0f..10f) }
    RangeSlider(
        value = currentRange,
        onValueChange = {currentRange = it},
        valueRange = 0f..10f,
        steps = 9,
        )
    Text(text = "Valor inferior ${currentRange.start}")
    Text(text = "Valor inferior${currentRange.endInclusive}")
}