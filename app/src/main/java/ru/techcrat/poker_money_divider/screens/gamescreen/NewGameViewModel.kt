package ru.techcrat.poker_money_divider.screens.gamescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.techcrat.poker_money_divider.models.CurrentPlayer

class NewGameViewModel() : ViewModel() {


    val players = MutableStateFlow(mutableListOf<CurrentPlayer?>())

    val isVisible = MutableStateFlow(false)
    fun addPlayer(player: CurrentPlayer) {
        players.value.add(player)
        viewModelScope.launch {
            players.collectLatest { isVisible.value = it.isNotEmpty() }
        }
    }
}
