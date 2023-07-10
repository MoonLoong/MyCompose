package com.syl.mycompose.study.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.syl.mycompose.study.constant.LocalKey
import com.tencent.mmkv.MMKV


enum class Theme {
    Default, Green, Purple
}

/**
 * 保存全局主题
 */
val themeState by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    val themeString = MMKV.defaultMMKV().decodeString(LocalKey.KEY_USER_THEME, Theme.Default.name)
    mutableStateOf(Theme.valueOf(themeString ?: "default"))
}

/**
 * material3适配
 * 默认：适配
 */
val dynamicThemeState by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    val dynamicColor = MMKV.defaultMMKV().decodeBool(LocalKey.KEY_DYNAMIC_COLOR, true)
    mutableStateOf(dynamicColor)
}


/**
 * 自定义的主题
 */
@Composable
fun AndroidTemplateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = dynamicThemeState.value,
    content: @Composable () -> Unit
) {
    val theme by remember { themeState }

    val lightColors = when (theme) {
        Theme.Default -> LightColors
        Theme.Green -> com.syl.mycompose.study.ui.theme.green.LightColors
        Theme.Purple -> com.syl.mycompose.study.ui.theme.purple.LightColors

    }
    val darkColors = when (theme) {
        Theme.Default -> DarkColors
        Theme.Green -> com.syl.mycompose.study.ui.theme.green.DarkColors
        Theme.Purple -> com.syl.mycompose.study.ui.theme.purple.DarkColors
    }

    val colorScheme = when {
        // 动态主题
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // 深色主题
        darkTheme -> darkColors
        // 浅色主题
        else -> lightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            // 设置状态栏颜色
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}