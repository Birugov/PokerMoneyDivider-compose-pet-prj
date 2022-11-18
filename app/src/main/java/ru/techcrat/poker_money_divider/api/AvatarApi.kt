package ru.techcrat.poker_money_divider.api

import retrofit2.http.GET
import retrofit2.http.Path

interface AvatarApi {
    @GET("/api/miniavs/{seed}.svg")
    suspend fun createAvatar(
        @Path("seed") name: String
    ): String
}
