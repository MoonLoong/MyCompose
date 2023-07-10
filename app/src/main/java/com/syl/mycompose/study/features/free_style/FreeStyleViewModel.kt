package com.syl.mycompose.study.features.free_style

import com.syl.mycompose.study.core.base.BaseViewModel
import timber.log.Timber


class FreeStyleViewModel : BaseViewModel() {
    suspend fun testFunc() {
        Timber.tag("ProfileMainViewModel").d("执行了")
    }
}