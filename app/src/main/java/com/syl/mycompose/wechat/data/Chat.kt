package com.syl.mycompose.wechat.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * 聊天对象
 */
class Chat(var friend: User, var msgs: MutableList<Msg>) {
}

/**
 * 消息
 */
class Msg(val from: User, val text: String, val time: String) {
  var read: Boolean by mutableStateOf(true)
}

/**
 * 如果定义data class 的实体类，必须添加 @Stable 或 @Immutable 注释表示不可变
 */
@Stable
@Immutable
data class Aa(val aa: String)