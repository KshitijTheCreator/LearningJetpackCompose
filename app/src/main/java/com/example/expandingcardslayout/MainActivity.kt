package com.example.expandingcardslayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandingcardslayout.ui.theme.ExpandingCardsLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandingCardsLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}


@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) {"$it"}
){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
        items(items = names){ name ->
            Greeting(name = name)
        }
    }
}


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit){
    var displayOnboardingScreen by remember {mutableStateOf(true)}
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "Welcome to The Application")
        OutlinedButton(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked,
            border = BorderStroke(1.dp, Color.Blue),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
        ) {

            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingScreenPreview(){
    ExpandingCardsLayoutTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember{mutableStateOf(false)}
    val extraPadding by animateDpAsState ( if(expanded.value) 48.dp else 0.dp)

    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello")
                Text(text = name)
            }
            OutlinedButton(onClick = {expanded.value = !expanded.value },
                border = BorderStroke(1.dp, Color.Blue),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Text(if(expanded.value) "Show less" else "show more")
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
){
    var displayOnboardingScreen by rememberSaveable {mutableStateOf(true)}
    Surface(modifier){
        if(displayOnboardingScreen){
            OnboardingScreen(onContinueClicked = {displayOnboardingScreen = false})
        }
        else{
            Greetings()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExpandingCardsLayoutTheme {
        Greeting("Android")
    }
}