package ru.techcrat.poker_money_divider.screens.gamedetailsscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GameDetailsScreen(){
    Scaffold(Modifier.fillMaxSize(), backgroundColor = Color.White) {
        Text(text = "text".repeat(100))
    }
}
