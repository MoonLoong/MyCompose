package com.syl.mycompose.study.custom

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.syl.mycompose.R
import com.syl.mycompose.study.features.main.LocalSnackbarHostState
import kotlinx.coroutines.launch

var lastBackTime: Long = 0L

@Composable
fun MyBackHandler(snackbarHostState: SnackbarHostState = LocalSnackbarHostState.current) {

    val ctx = LocalContext.current

    val scope = rememberCoroutineScope()

    val exitApp = stringResource(id = R.string.exit_app_tips)

    BackHandler(enabled = true) {
        val curTime = System.currentTimeMillis()
        if (curTime - lastBackTime > 2000) {
            scope.launch {
                // compose 支持子线程更新UI,showSnackbar 是挂起函数
                snackbarHostState.showSnackbar(exitApp)
            }
            lastBackTime = curTime
        } else {
            (ctx as? Activity)?.finish()
        }
    }
}