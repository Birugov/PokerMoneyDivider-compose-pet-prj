package ru.techcrat.poker_money_divider.screens.gamescreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.techcrat.poker_money_divider.api.AvatarApi
import ru.techcrat.poker_money_divider.models.CurrentPlayer

class NewGameViewModel(private val api: AvatarApi) : ViewModel() {


    suspend fun createAvatar(username:String) = api.createAvatar(username)

}
