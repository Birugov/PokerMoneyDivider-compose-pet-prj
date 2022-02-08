package ru.techcrat.poker_money_divider.constantData

import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.models.Card
import ru.techcrat.poker_money_divider.models.Combination


val COMBINATIONS = listOf(
    Combination(
        R.string.pair_combination, listOf(

            Card(id = R.drawable.four_diamonds_card, " R.drawable.four_diamonds_card"),
            Card(id = R.drawable.foure_spades_card, "R.drawable.foure_spades_card"),
            Card(
                id = R.drawable.eight_hearts_card_transparent,
                "R.drawable.eight_hearts_card_transparent"
            ),
            Card(
                id = R.drawable.five_clubs_card_transparent,
                "R.drawable.five_clubs_card_transparent"
            ),
            Card(
                id = R.drawable.seven_diamonds_card_transparent,
                "R.drawable.seven_diamonds_card_transparent"
            )
        ),
        R.string.pair_combination_description
    ),
    Combination(
        name = R.string.two_pairs_combination,
        imageId = listOf(
            Card(id = R.drawable.four_diamonds_card, "R.drawable.four_diamonds_card"),
            Card(id = R.drawable.foure_spades_card, "R.drawable.foure_spades_card"),
            Card(id = R.drawable.six_hearts_card, "R.drawable.six_hearts_card"),
            Card(id = R.drawable.six_spades_card, "R.drawable.six_spades_card"),
            Card(
                id = R.drawable.seven_diamonds_card_transparent,
                "R.drawable.seven_diamonds_card_transparent"
            )
        ),
        descriptionResId = R.string.two_pairs_combination_description
    ),

    Combination(
        name = R.string.set_combination,
        imageId = listOf(
            Card(id = R.drawable.six_spades_card, "R.drawable.six_spades_card"),
            Card(id = R.drawable.six_hearts_card, "R.drawable.six_hearts_card"),
            Card(id = R.drawable.six_diamonds_card, "R.drawable.six_diamonds_card"),
            Card(
                id = R.drawable.seven_diamonds_card_transparent,
                "R.drawable.seven_diamonds_card_transparent"
            ),
            Card(
                id = R.drawable.eight_hearts_card_transparent,
                "R.drawable.eight_hearts_card_transparent"
            )
        ),
        descriptionResId = R.string.set_combination_description
    ),

    Combination(
        name = R.string.straight_combination,
        imageId = listOf(
            Card(id = R.drawable.six_diamonds_card, "R.drawable.six_diamonds_card"),
            Card(id = R.drawable.seven_hearts_card, "R.drawable.seven_hearts_card"),
            Card(id = R.drawable.eight_clubs_card, "R.drawable.eight_clubs_card"),
            Card(id = R.drawable.ten_clubs_card, "R.drawable.ten_clubs_card"),
            Card(
                id = R.drawable.ten_clubs_card,
                "R.drawable.ten_clubs_card"
            )
        ),
        descriptionResId = R.string.straight_combination_description
    ),

    Combination(
        name = R.string.flush_combination,
        imageId = listOf(
            Card(id = R.drawable.seven_hearts_card, "R.drawable.seven_hearts_card"),
            Card(id = R.drawable.ace_hearts_card, "R.drawable.ace_hearts_card"),
            Card(id = R.drawable.six_hearts_card, "R.drawable.six_hearts_card"),
            Card(id = R.drawable.king_hearts_card, "R.drawable.king_hearts_card"),
            Card(
                id = R.drawable.ten_hearts_card,
                "R.drawable.ten_hearts_card"
            )
        ),
        descriptionResId = R.string.flush_combination_description
    ),

    Combination(
        name = R.string.fullhouse_combination,
        imageId = listOf(
            Card(id = R.drawable.foure_spades_card, "R.drawable.foure_spades_card"),
            Card(id = R.drawable.four_diamonds_card, "R.drawable.four_diamonds_card"),
            Card(id = R.drawable.six_hearts_card, "R.drawable.six_hearts_card"),
            Card(id = R.drawable.six_diamonds_card, "R.drawable.six_diamonds_card"),
            Card(
                id = R.drawable.six_spades_card,
                "R.drawable.six_spades_card"
            )

        ),
        descriptionResId = R.string.fullhouse_combination_description
    ),

    Combination(
        name = R.string.quads_combination,
        imageId = listOf(
            Card(id = R.drawable.queen_diamonds_card, "R.drawable.queen_diamonds_card"),
            Card(id = R.drawable.queen_hearts_card, "R.drawable.queen_hearts_card"),
            Card(id = R.drawable.queen_clubs_card, "R.drawable.queen_clubs_card"),
            Card(id = R.drawable.queen_squads_card, "R.drawable.queen_squads_card"),
            Card(
                id = R.drawable.five_clubs_card_transparent,
                "R.drawable.five_clubs_card_transparent"
            )
        ),
        descriptionResId = R.string.quads_combination_description
    )


)


