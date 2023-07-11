package com.syl.mycompose.study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.syl.mycompose.R
import com.syl.mycompose.study.features.main.MainPage
import com.syl.mycompose.study.features.main.rememberAppState
import com.syl.mycompose.study.log.TAG_INFO
import com.syl.mycompose.study.log.TAG_LIFECYCLE
import timber.log.Timber


/**
 * 首页 （单Activity模式）
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyCompose_Splash)
        super.onCreate(savedInstanceState)

        Timber.tag(TAG_LIFECYCLE).d("onCreate")
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            // 创建appState，使用 navController 进行页面导航
            val appState = rememberAppState(windowSizeClass = windowSizeClass)
            val navHostController = appState.navController
            val currentBackStackEntryAsState by navHostController.currentBackStackEntryAsState()
            // 启动协程打印日志
            LaunchedEffect(navHostController, currentBackStackEntryAsState) {
                Timber.tag(TAG_INFO).d(
                    "当前页面栈信息：${currentBackStackEntryAsState?.destination?.route}," +
                            "栈内页面数量：${navHostController.backQueue.map { it.destination.route }}"
                )
            }
            MainPage(windowSizeClass, appState = appState)
        }
    }
}