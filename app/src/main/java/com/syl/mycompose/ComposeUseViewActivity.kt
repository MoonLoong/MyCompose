package com.syl.mycompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


/**
 * Compose中使用View
 */
class ComposeUseViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShowWebView()
        }
    }
}

@SuppressLint("SetTextI18n")
@Composable
private fun ComposeShowWebView() {
    val state = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // widget.Button
        AndroidView(
            factory = { ctx ->
                android.widget.Button(ctx).apply {
                    text = "你点我试试"
                    background = ctx.getDrawable(R.drawable.bg_android_view_shape)
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    setOnClickListener {
                        state.value++
                    }
                }
            }, modifier = Modifier
                .padding(8.dp)
                .size(200.dp, 50.dp)
        )

        // widget.TextView
        AndroidView(
            factory = { ctx ->
                TextView(ctx).apply {
                    gravity = Gravity.CENTER
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                }
            },
            update = {
                // Update TextView with the current state value
                it.text = "你已经点完 " + state.value.toString() + " 次了"
            })
    }
}

@Preview(showBackground = true)
@Composable
fun TestComposeShowView() {
    ComposeShowWebView()
}