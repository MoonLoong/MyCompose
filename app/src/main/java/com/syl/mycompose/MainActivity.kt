package com.syl.mycompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.sp
import com.syl.mycompose.ui.compose.ChatPage
import com.syl.mycompose.ui.compose.Home
import com.syl.mycompose.ui.theme.WeComposeTheme
import com.syl.mycompose.ui.theme.green1
import com.syl.mycompose.ui.theme.small


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
    Box(
        modifier = modifier
            .size(100.dp)
            .background(green1),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
//                .wrapContentSize(align = Alignment.Center)
                .fillMaxSize()
                .padding(10.dp)
//                .clip(medium)
                .background(brush = brushVertical, small)
                .padding(5.dp)
//            .clip(small) 放这里就不生效了
//            .drawBehind {// 绘制圆形背景
//                drawCircle(
//                    color = Color.Green,
//                    radius = this.size.minDimension
//                )
//            }
                .clickable {
                    Toast
                        .makeText(context, "this is a toast", Toast.LENGTH_SHORT)
                        .show()
                },
            fontSize = 20.sp
        )
    }

}

@Preview(showBackground = true, heightDp = 200, widthDp = 200)
@Composable
fun GreetingPreview() {
    WeComposeTheme {
        Greeting("Android")
    }
}