package ru.techcrat.poker_money_divider.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.techcrat.poker_money_divider.models.CurrentPlayer
import ru.techcrat.poker_money_divider.room.converters.Converters
import ru.techcrat.poker_money_divider.room.converters.DateConverter
import java.util.*

@Entity
data class GameDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "players")
    @TypeConverters(Converters::class)
    val players: List<CurrentPlayer>,

    @ColumnInfo(name = "gameDate")
    @TypeConverters(DateConverter::class)
    val gameDate:Date,

    @ColumnInfo(name = "bank")
    val bank: Int,

    @ColumnInfo(name = "isEnded")
    val isEnded:Boolean = false
)
