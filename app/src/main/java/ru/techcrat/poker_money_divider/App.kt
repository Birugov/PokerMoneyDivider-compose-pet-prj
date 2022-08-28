package ru.techcrat.poker_money_divider

import android.app.Application
import androidx.room.Room
import org.koin.core.context.startKoin
import ru.techcrat.poker_money_divider.koin.roomDatabaseModule
import ru.techcrat.poker_money_divider.koin.viewModelModule
import ru.techcrat.poker_money_divider.room.PokerDividerDatabase


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                roomDatabaseModule,
                viewModelModule
            )
        }
    }
}
