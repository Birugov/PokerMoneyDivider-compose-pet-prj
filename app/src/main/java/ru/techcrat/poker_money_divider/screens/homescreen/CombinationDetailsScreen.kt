package ru.techcrat.poker_money_divider.screens.homescreen

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.models.Card
import ru.techcrat.poker_money_divider.models.Combination

@Composable
fun CombinationDetailsScreen(combination: String) {
    Log.d("combination_1", combination)
    val hand = Gson().fromJson(combination, Combination::class.java)
    Column(Modifier.padding(top = 200.dp, start = 0.dp, end = 0.dp)) {
        CombinationBox(hand = hand)
        NameTextBox(hand = hand)
    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CombinationBox(hand: Combination) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            Modifier.matchParentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            hand.imageId.forEach {
                var cardFace by remember {
                    mutableStateOf(CardFace.Front)
                }
                FlipCard(cardFace = cardFace,
                    onClick = { cardFace = cardFace.next },
                    modifier = Modifier
                        .aspectRatio(1f),
                    front = {
                        Image(
                            painter = painterResource(id = it.id),
                            contentDescription = "description"
                        )

                    },
                    back = {
                        Image(
                            painter = painterResource(id = backwardChanger(it)),
                            contentDescription = "description"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun NameTextBox(hand: Combination) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = hand.name),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun DescriptionTextBox(hand: Combination) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = hand.descriptionResId), fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp),
            fontFamily = FontFamily.SansSerif,
        )
    }


}

enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@ExperimentalMaterialApi
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        )
    )
    Card(
        elevation = 0.dp,
        onClick = { onClick(cardFace) },
        modifier = modifier
            .graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation.value
                } else {
                    rotationY = rotation.value
                }
                cameraDistance = 12f * density
            },
    ) {
        if (rotation.value <= 90f) {
            Box(
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    .graphicsLayer {
                        if (axis == RotationAxis.AxisX) {
                            rotationX = 180f
                        } else {
                            rotationY = 180f
                        }
                    },
            ) {
                back()
            }
        }
    }
}

fun backwardChanger(card: Card): Int {
    return if (card.resourceName.contains("transparent")) {
        R.drawable.backward_card_transparent
    } else {
        R.drawable.backward_card
    }
}
