package ru.techcrat.poker_money_divider.koin

import androidx.room.Room
import org.koin.dsl.module
import ru.techcrat.poker_money_divider.room.PokerDividerDatabase
import org.koin.android.ext.koin.androidContext


val roomDatabaseModule = module{

    single{
        Room.databaseBuilder(
            androidContext(),
            PokerDividerDatabase::class.java,
            "poker_divider_name"
        ).build()
    }
}
