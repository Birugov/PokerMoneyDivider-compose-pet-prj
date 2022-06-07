package ru.techcrat.poker_money_divider.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "players")
    val players: List<PlayerDto>
)
