package ru.techcrat.poker_money_divider.koin

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import org.koin.dsl.module
import ru.techcrat.poker_money_divider.room.PokerDividerDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import ru.techcrat.poker_money_divider.api.AvatarApi
import ru.techcrat.poker_money_divider.screens.gamescreen.NewGameViewModel


val roomDatabaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            PokerDividerDatabase::class.java,
            "poker_divider_name"
        ).build()
    }

}

val viewModelModule = module {

    viewModel { NewGameViewModel(get()) }
}
