package ru.techcrat.poker_money_divider.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.nav.NavigationItem


@Composable
fun StartNewGameScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { navigateToNewScreen(navController, NavigationItem.Game.route) },
            Modifier.size(240.dp),
            shape = CircleShape,
            colors = buttonColors(backgroundColor = colorResource(id = R.color.gold))
        ) {
            Text(text = "New game", fontSize = 36.sp, color = Color.White)
        }
    }
}

private fun navigateToNewScreen(navController: NavHostController, route: String) {
    navController.navigate(route) {
        navController.graph.route?.let { rout ->
            popUpTo(rout) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
