package ru.techcrat.poker_money_divider.screens.homescreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.models.Combination
import ru.techcrat.poker_money_divider.nav.NavigationItem

@Composable
fun HomeScreen(list: List<Combination>, context: Context, navController: NavHostController) {
    InitLazyColumn(list = list, navController)
}

@Composable
fun InitLazyColumn(list: List<Combination>, navController: NavHostController) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(list) { item ->
            Card(elevation = 12.dp, modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
                .height(40.dp)
                .clickable {
                    navigateToNewScreen(
                        navController,
                        NavigationItem.CombinationDetails.route,
                        item
                    )
                }
            ) {
                Row(
                    Modifier
                        .height(20.dp)
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "gfgdfgd",
                        Modifier.padding(8.dp)
                    )
                    Text(
                        text = stringResource(id = item.name),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                }
            }

        }
    }

}

private fun navigateToNewScreen(
    navController: NavHostController,
    route: String,
    combination: Combination
) {
    Log.d("combination_1", Gson().toJson(combination))
    navController.navigate("$route/${Gson().toJson(combination)}") {
        navController.graph.route?.let { rout ->
            popUpTo(rout) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

