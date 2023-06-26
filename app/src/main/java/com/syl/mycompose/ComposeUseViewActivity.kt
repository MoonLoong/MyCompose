package com.syl.mycompose

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
private fun ComposeShowWebView() {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl("https://www.baidu.com")
        }
    }, modifier = Modifier.fillMaxSize())
}

@Preview(showBackground = true)
@Composable
fun TestComposeShowView() {
    ComposeShowWebView()
}