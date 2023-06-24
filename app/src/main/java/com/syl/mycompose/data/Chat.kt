package com.syl.mycompose.data

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