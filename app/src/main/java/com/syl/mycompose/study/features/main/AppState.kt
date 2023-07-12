package com.syl.mycompose.study.features.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * StateHolder 管理状态
 * 根据 windowSizeClass 创建 navController
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberAnimatedNavController()
): AppState {
    return remember(navController, windowSizeClass) {
        AppState(navController, windowSizeClass)
    }
}

/**
 * @Stable 和 @Immutable，两者都继承自@StableMarker
 * 告诉编译器，我是稳定的不变的状态
 * 可以跳过不必要的重组
 * 前者能力完全覆盖了后者，未来Immutable有可能会被移除，建议大家优先选择使用Stable。
 */
@Stable
class AppState(
    val navController: NavHostController,
    private val windowSizeClass: WindowSizeClass
) {
    /**
     * 对窗口大小类别执行逻辑来决定是否使用导航栏
     * 横屏和竖屏时候显示底部导航
     */
    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
}