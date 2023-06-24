package com.syl.mycompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syl.mycompose.ui.compose.ChatPage
import com.syl.mycompose.ui.compose.Home
import com.syl.mycompose.ui.theme.WeComposeTheme


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeComposeTheme(viewModel.theme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Home(viewModel)
                    ChatPage()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!viewModel.endChat()) {
            super.onBackPressed()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val brushVertical = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            Color.Blue,
            Color.Red
        )
    )
    Text(
        text = "Hello $name!",
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
            .padding(10.dp)
            .background(brush = brushVertical)
            .padding(5.dp)
//            .drawBehind {
//                drawCircle(
//                    color = Color.Green,
//                    radius = this.size.maxDimension
//                )
//            }
            .clickable {
                Toast
                    .makeText(context, "this is a toast", Toast.LENGTH_SHORT)
                    .show()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeComposeTheme {
        Greeting("Android")
    }
}