package ru.techcrat.poker_money_divider.nav

import ru.techcrat.poker_money_divider.R

sealed class NavigationItem(var route: String, var icon: Int?, var title: String?) {
    object Home : NavigationItem("home_screen", R.drawable.home_page_menu_image, "Home")
    object GameList : NavigationItem("games_list", R.drawable.history_page_image, "History")
    object Game : NavigationItem("games_list", R.drawable.game_page_menu_image, "Game")
    object NewScreen: NavigationItem("new_screen", null, null)
}
