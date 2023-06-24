package com.syl.mycompose.data

import androidx.annotation.DrawableRes
import com.syl.mycompose.R


/**
 * 用户信息
 */
class User(
  val id: String,
  val name: String,
  @DrawableRes val avatar: Int
) {
  companion object {
    val Me: User = User("xiatian", "夏天", R.drawable.avatar_1)
  }
}