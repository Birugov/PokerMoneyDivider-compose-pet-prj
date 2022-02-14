package ru.techcrat.poker_money_divider.nav

import ru.techcrat.poker_money_divider.R

sealed class NavigationItem(var route: String, var icon: Int?, var title: String?) {
    object Home : NavigationItem("home_screen", R.drawable.combination_nav_icon, "Combinations")
    object GameList : NavigationItem("history_screen", R.drawable.history_page_image, "History")
    object Game : NavigationItem("games_list", R.drawable.game_page_menu_image, "Game")
    object GameDetails: NavigationItem("new_screen", null, null)
    object CombinationDetails : NavigationItem("combination_screen/{combination}", null, null )
}
