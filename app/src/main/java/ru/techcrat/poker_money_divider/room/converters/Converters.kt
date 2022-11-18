package ru.techcrat.poker_money_divider.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.techcrat.poker_money_divider.models.CurrentPlayer
import java.util.*

class Converters {

    @TypeConverter
    fun fromCurrentPlayerToString(players: List<CurrentPlayer>): String =
        players.joinToString("SEPARATOR") { Gson().toJson(it) }

    @TypeConverter
    fun fromStringToCurrentPlayer(playersJson: String): List<CurrentPlayer> =
        playersJson.split("SEPARATOR").map{ Gson().fromJson(it, CurrentPlayer::class.java) }



}
