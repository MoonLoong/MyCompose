package com.syl.mycompose

import android.app.Activity
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.syl.mycompose.ui.theme.WeComposeTheme

class HomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViewById<ComposeView>(R.id.root).setContent {
            WeComposeTheme(WeComposeTheme.Theme.Light) {
                HomeView()
            }
        }
    }

    @Composable
    private fun HomeView() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "View中使用Compose",
                modifier = Modifier.wrapContentSize(),
                color = WeComposeTheme.colors.textPrimaryMe,
                fontSize = 26.sp
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ShowTestHomeActivity() {
        WeComposeTheme(WeComposeTheme.Theme.Light) {
            HomeView()
        }
    }
}

