package ru.techcrat.poker_money_divider

import android.app.StatusBarManager
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.techcrat.poker_money_divider.constantData.COMBINATIONS
import ru.techcrat.poker_money_divider.nav.NavigationItem
import ru.techcrat.poker_money_divider.screens.gamedetailsscreen.GameDetailsScreen
import ru.techcrat.poker_money_divider.screens.homescreen.CombinationDetailsScreen
import ru.techcrat.poker_money_divider.screens.homescreen.HomeScreen
import ru.techcrat.poker_money_divider.ui.theme.Poker_money_dividerTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
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
            backgroundColor = colorResource(id = R.color.gold),
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute==item.route,
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
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.2f),
                    alwaysShowLabel = true
                )

            }

        }

    }

    @Composable
    fun MainScreen() {
        ProvideWindowInsets {
            val controller = rememberSystemUiController()
            controller.setStatusBarColor(Color.Transparent, true)
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .systemBarsPadding(true),
                bottomBar = { BottomNavigationBar(navController = navController) }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavigationGraph(navController = navController)
                }

            }
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
                HomeScreen(
                    list = COMBINATIONS
                )
            }
            composable(NavigationItem.GameList.route) {
                ListScreen(list = listScreen())
            }
            composable(NavigationItem.Game.route) {
                ListScreen(list = listScreen())
            }

            composable(NavigationItem.GameDetails.route) {
                GameDetailsScreen()
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

}
