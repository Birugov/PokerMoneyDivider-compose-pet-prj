package ru.techcrat.poker_money_divider.koin

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.techcrat.poker_money_divider.BuildConfig
import ru.techcrat.poker_money_divider.api.AvatarApi

val networkModule = module {

    single {
        OkHttpClient.Builder().build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.AVATAR_ENDPOINT)
            .build()
            .create(AvatarApi::class.java)
    }
}
