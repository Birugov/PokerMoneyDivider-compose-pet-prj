package ru.techcrat.poker_money_divider.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
entities =[PlayerDto::class],
version = 1
)
abstract class PokerDividerDatabase: RoomDatabase() {

}
