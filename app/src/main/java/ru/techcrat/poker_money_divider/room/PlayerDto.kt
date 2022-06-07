package ru.techcrat.poker_money_divider.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerDto(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "player_name")
    val name:String,
    @ColumnInfo(name = "player_avatar")
    val avatar:String?
)
