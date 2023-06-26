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
import com.syl.mycompose.ui.theme.red1

/**
 * View中使用Compose
 * 在工程中引入Compose，添加Compose相关依赖并开启compose，Xml中根布局使用ComposeView，并定义id
 */
class ViewUseComposeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        /**
         * 所有Compose代码逻辑都承载在ComposeView之上,对原有基于View的代码侵入极小,可以放心将已有项目逐步迁移到Compose。
         */
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
                color = red1,
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

