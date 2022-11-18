package ru.techcrat.poker_money_divider.screens.gamescreen

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.viewModel
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.models.CurrentPlayer

val openDialog = mutableStateOf(false)
var players = mutableStateOf(listOf<CurrentPlayer?>())

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalFoundationApi
@Composable
fun NewGameScreen() {
    val vm: NewGameViewModel by viewModel()
    val lifeCycleOwner = LocalLifecycleOwner.current
    var visible by remember { mutableStateOf(false) }
    dialog(vm)

    visible = players.value.isNotEmpty()


    Box(Modifier.fillMaxSize()) {

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            columns = GridCells.Adaptive(minSize = 200.dp)

        ) {
            items(imagePlaceHolder()) {
                Card(
                    Modifier
                        .padding(10.dp)
                        .clickable {
                            openDialog.value = true
                        }
                        .height(100.dp)
                ) {


                    Box(Modifier.fillMaxSize()) {

                        GlideImage(
                            imageModel = "https://avatars.dicebear.com/api/miniavs/Нияз.jpg",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.BottomEnd)
                        )

                        Column(
                            Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxSize(0.9f),

                            ) {
                            Text(
                                fontSize = 13.sp,
                                text = "Семакин Артем",
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(60)
                                    )
                                    .background(
                                        colorResource(id = R.color.gold).copy(
                                            alpha = 0.3f
                                        )
                                    )
                                    .padding(8.dp)

                            )
                            Text(
                                text = "bank",
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .clip(
                                        RoundedCornerShape(60)
                                    )
                                    .background(
                                        colorResource(id = R.color.gold).copy(
                                            alpha = 0.3f
                                        )
                                    )
                                    .padding(8.dp)

                            )
                        }

//
//                            Box(
//                                contentAlignment = Alignment.BottomEnd,
//                                modifier = Modifier.fillMaxSize()
//                            ) {
////                            Image(
////                                painter = painterResource(id = it),
////                                contentDescription = null,
////                                modifier = Modifier
////                                    .clip(
////                                        CircleShape
////                                    )
////                                    .size(80.dp)
////                                    .padding(end = 10.dp, bottom = 16.dp)
////                                    .border(1.dp, Color.LightGray, CircleShape)
////                            )
//
//
//                                GlideImage(
//                                    imageModel = "https://avatars.dicebear.com/api/miniavs/Нияз.jpg",
//                                    contentScale = ContentScale.Inside,
//                                    modifier = Modifier.size(80.dp)
//                                )
//
//                            }

                    }

                }
            }

            item {
                Card(
                    Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(bounded = true, color = Color.Black)
                        ) {
                            openDialog.value = true
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.LightGray.copy(alpha = 0.4f)),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Gray)


                    )
                }
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = visible,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 10.dp)
                    .shadow(elevation = 10.dp, RoundedCornerShape(60))
                    .clip(RoundedCornerShape(60))
            ) {
                LazyRow(
                    Modifier.animateContentSize()
                )
                {
                    items(players.value) { player ->

                        Image(
                            painter = painterResource(player?.avatar ?: R.drawable.ace_hearts_card),
                            contentDescription = "No avatar",
                            Modifier
                                .clip(CircleShape)
                                .size(80.dp)
                                .padding(8.dp)
                                .border(1.dp, Color.LightGray, CircleShape)
                        )

                    }
                }
            }

        }


    }


}

@Composable
fun dialog(viewModel: NewGameViewModel) {
    val sum = remember { mutableStateOf("") }
    if (openDialog.value)
        AlertDialog(
            title = {
                Text(text = "Bank", Modifier.fillMaxWidth(), fontSize = 40.sp)
            },
            text = {
                Column(Modifier.fillMaxWidth()) {
                    Text(text = "Please, enter your starting capital", fontSize = 16.sp)
                    TextField(
                        textStyle = TextStyle(fontSize = 20.sp),
                        value = sum.value,
                        onValueChange = { sum.value = it },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(id = R.color.gold),
                            backgroundColor = Color.Transparent,
                            cursorColor = colorResource(id = R.color.gold)

                        )
                    )
                }
            },


            onDismissRequest = {

            },
            buttons = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openDialog.value = false
                        players.value =
                            players.value + CurrentPlayer(2, R.drawable.ace_hearts_card, "fgdfgdfg")
                    }, colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.gold)
                    )

                ) {
                    Text("test")
                    imagePlaceHolder()
                }
            }


        )
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PreviewList() {
    NewGameScreen()
}

private fun imagePlaceHolder() = listOf(
    R.drawable.five_clubs_card_transparent,
    R.drawable.queen_squads_card,
    R.drawable.queen_diamonds_card,
    R.drawable.six_diamonds_card,
    R.drawable.six_diamonds_card,
    R.drawable.six_diamonds_card,
    R.drawable.six_diamonds_card,
    R.drawable.six_diamonds_card,
    R.drawable.six_diamonds_card,
    R.drawable.six_diamonds_card
)




