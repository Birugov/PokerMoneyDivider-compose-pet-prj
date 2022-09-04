package ru.techcrat.poker_money_divider.screens.gamescreen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
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
import ru.techcrat.poker_money_divider.models.CurrentPlayer
import ru.techcrat.poker_money_divider.models.Player

val openDialog = mutableStateOf(false)

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalFoundationApi
@Composable
fun NewGameScreen() {
    val vm: NewGameViewModel by viewModel()
    val lifeCycleOwner = LocalLifecycleOwner.current
    var visible by remember { mutableStateOf(false) }
    dialog(vm)

    LaunchedEffect(true) {
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                vm.isVisible.collect {
                    visible = it
                }
            }
        }
    }


    Box(Modifier.fillMaxSize()) {
//        AnimatedVisibility(
//            visible = visible
//        ) {
//            Text(
//                text = "Dfdsfsfd",
//                Modifier
//                    .fillMaxSize()
//                    .background(Color.White))
//        }

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()

        ) {
            items(imagePlaceHolder()) {
                Card(
                    Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .clickable {
                            openDialog.value = true
                        }
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
        AnimatedVisibility(modifier = Modifier.align(Alignment.BottomCenter), visible = visible, enter = scaleIn(), exit = scaleOut()) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 10.dp)
                        .shadow(elevation = 10.dp, RoundedCornerShape(60))
                        .clip(RoundedCornerShape(60))
                ) {
                    LazyRow(
                    )
                    {
                        items(imagePlaceHolder()) {

                            Image(
                                painter = painterResource(R.drawable.ace_hearts_card),
                                contentDescription = "No avatar",
                                Modifier
                                    .size(80.dp)
                                    .padding(8.dp)
                                    .clip(CircleShape)
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
        if (openDialog.value)
            AlertDialog(
                title = {
                    Text(text = "Bank", Modifier.fillMaxWidth(), fontSize = 40.sp)
                },
                text = {
                    Column(Modifier.fillMaxWidth()) {
                        val sum = remember { mutableStateOf("") }
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
                            viewModel.addPlayer(CurrentPlayer(2, null, " gfgdfg"))
                        }, colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.gold)
                        )

                    ) {
                        Text("test")
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




