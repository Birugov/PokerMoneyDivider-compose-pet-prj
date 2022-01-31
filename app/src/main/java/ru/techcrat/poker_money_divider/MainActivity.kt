package ru.techcrat.poker_money_divider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.techcrat.poker_money_divider.nav.NavigationItem
import ru.techcrat.poker_money_divider.screens.homescreen.HomeScreen
import ru.techcrat.poker_money_divider.screens.homescreen.generateData
import ru.techcrat.poker_money_divider.ui.theme.Poker_money_dividerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }

    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val items = listOf(
            NavigationItem.Game,
            NavigationItem.Home,
            NavigationItem.GameList
        )
        BottomNavigation(
            backgroundColor = colorResource(id = R.color.design_default_color_primary),
            contentColor = Color.White
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    selected = false,
                    onClick = {
                        navigateToNewScreen(navController, item.route)
                    },
                    icon = {
                        item.icon?.let { painterResource(id = it) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = item.title
                            )
                        }
                    },
                    label = { item.title?.let { Text(text = it) } },
                    selectedContentColor = Color.White,
                    alwaysShowLabel = true
                )
            }

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Poker_money_dividerTheme {
            Greeting("Android")
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) {
            NavigationGraph(navController = navController)
        }
    }

    @Preview
    @Composable
    fun ScreenPreview() {
        MainScreen()
    }

    @Composable
    fun ListScreen(list: List<String>) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(list.size) { elem ->
                SimpleTextListElem(text = list[elem])
            }
        }
    }

    @Composable
    fun SimpleTextListElem(text: String) {
        Text(
            text = text, style = MaterialTheme.typography.h5, modifier = Modifier.size(
                100.dp
            )
        )
    }


    fun listScreen(): MutableList<String> {
        val list = mutableListOf<String>()
        for (i in 'a'..'z') {
            list.add(i.toString())
        }
        return list
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                ListScreen(list = listScreen())
            }
            composable(NavigationItem.GameList.route) {
                ListScreen(list = listScreen())
            }
            composable(NavigationItem.Game.route) {
                HomeScreen(
                    list = generateData(),
                    this@MainActivity,
                    navController
                )
            }

            composable(NavigationItem.NewScreen.route) {
                NewScreen()
            }
        }
    }

    private fun navigateToNewScreen(navController: NavHostController, route: String) {
        navController.navigate(route) {
            navController.graph.startDestinationRoute?.let { rout ->
                popUpTo(rout) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    @Composable
    fun NewScreen(){
        Scaffold(Modifier.fillMaxSize(), backgroundColor = Color.White) {
            Text(text = "text".repeat(100))
        }
    }
}



