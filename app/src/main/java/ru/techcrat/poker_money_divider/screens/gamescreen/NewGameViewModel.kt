package ru.techcrat.poker_money_divider.screens.gamescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.techcrat.poker_money_divider.models.Player

class NewGameViewModel() : ViewModel() {

    val players = MutableStateFlow(mutableListOf<Player?>())

    val isVisible = MutableStateFlow(true)
    fun addPlayer(player: Player) {
        players.value.add(player)
        viewModelScope.launch {
            players.collectLatest {isVisible.value = it.isEmpty() }
        }
    }
}
