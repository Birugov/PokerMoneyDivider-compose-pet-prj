package ru.techcrat.poker_money_divider.room

import androidx.room.*
import ru.techcrat.poker_money_divider.room.converters.Converters
import ru.techcrat.poker_money_divider.room.converters.DateConverter

@Database(
entities =[PlayerDto::class, GameDto::class],
version = 1,

)
@TypeConverters(Converters::class, DateConverter::class)
abstract class PokerDividerDatabase: RoomDatabase() {


}
