package ru.techcrat.poker_money_divider.screens.gamescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import org.koin.androidx.compose.viewModel
import ru.techcrat.poker_money_divider.R
import ru.techcrat.poker_money_divider.models.Player

@ExperimentalFoundationApi
@Composable
fun NewGameScreen() {
    val vm: NewGameViewModel by viewModel()
    val lifeCycleOwner = LocalLifecycleOwner.current
    var visible by remember { mutableStateOf(true)}

    LaunchedEffect(true){
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
            launch {
                vm.isVisible.collect {
                    visible = it
                }
            }
        }
    }

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (availablePlayersBox, currentGamePlayers, instructionBox) = createRefs()
        AnimatedVisibility(
            visible = visible
        ) {
            Text(text = "Dfdsfsfd",
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .constrainAs(instructionBox) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.top)
                    })
        }

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.constrainAs(currentGamePlayers) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(availablePlayersBox.top)
                height = Dimension.fillToConstraints
            }


        ) {
            items(imagePlaceHolder()) {
                Card(
                    Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                ) {
                    Column() {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.clip(
                                CircleShape
                            )
                        )
                        Text(text = "username")
                        Text(text = "bank")
                    }
                }
            }
        }

        Row(modifier = Modifier.constrainAs(availablePlayersBox) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Image(
                painter = painterResource(id = R.drawable.add_person_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clickable { }
            )

            LazyRow() {
                items(imagePlaceHolder()) {
                    Card(Modifier.clickable {
                        vm.addPlayer(Player(name = "Cfyzdfs"))
                    }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(R.drawable.ace_hearts_card),
                                contentDescription = "No avatar",
                                Modifier
                                    .clip(
                                        CircleShape
                                    )
                                    .size(80.dp)
                                    .padding(4.dp)
                            )
                            Text(
                                text = "Nickname",
                                fontSize = 28.sp,
                                modifier = Modifier.padding(4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
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




