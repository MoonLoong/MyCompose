package com.syl.mycompose.wechat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.syl.mycompose.R
import com.syl.mycompose.wechat.data.Chat
import com.syl.mycompose.wechat.data.Msg
import com.syl.mycompose.wechat.data.User
import com.syl.mycompose.theme.WeComposeTheme


/**
 * 首页ViewModel
 *
 * 注意⚠️：
 * ViewModel 应该与 UI 框架解耦，而 State 是属于 Compose 框架的，甚至难以在 Activity 或其他组件中直接使用。
 *
 * 「不推荐在 ViewModel 中直接使用 Compose 的 State」
 *  可以将ViewModel中的LiveData、Flow、RxJava转换成State，在Composable函数中使用
 */
class MainViewModel: ViewModel() {

    /**
     * 消息列表
     */
    var chats by mutableStateOf(
        listOf( // List<Chat>
            Chat(
                friend = User("gaolaoshi", "高老师", R.drawable.avatar_2),
                mutableStateListOf(
                    Msg(User("gaolaoshi", "高老师", R.drawable.avatar_2), "何以销烦暑", "14:20"),
                    Msg(User.Me, "端居一院中", "14:21"),
                    Msg(User("gaolaoshi", "高老师", R.drawable.avatar_2), "眼前无长物", "14:22"),
                    Msg(User.Me, "窗下有清风", "14:23"),
                    Msg(User("gaolaoshi", "高老师", R.drawable.avatar_2), "力尽不知热，但惜夏日长。", "14:24"),
                    Msg(User.Me, "炎炎日正午，灼灼火俱燃。", "14:25"),
                    Msg(User("gaolaoshi", "高老师", R.drawable.avatar_2), "绿树阴浓夏日长，楼台倒影入池塘。", "14:26"),
                    Msg(User.Me, "别得瑟了抓紧开空调吧，再不开就中暑了！", "14:27"),
                )
            ),
            Chat(
                friend = User("lilaoshi", "李老师", R.drawable.avatar_3),
                mutableStateListOf(
                    Msg(User("lilaoshi", "李老师", R.drawable.avatar_3), "哈哈哈", "13:48"),
                    Msg(User.Me, "哈哈哈哈哈", "13:48"),
                    Msg(User("lilaoshi", "李老师", R.drawable.avatar_3), "你笑个屁呀", "13:48").apply { read = false },
                )
            ),
        )
    )
    /**
     * 联系人列表
     */
    val contacts by mutableStateOf(
        listOf(
            User("gaolaoshi", "高老师", R.drawable.avatar_2),
            User("lilaoshi", "李老师", R.drawable.avatar_3)
        )
    )
    // 主题
    var theme by mutableStateOf(WeComposeTheme.Theme.Light)
    var currentChat: Chat? by mutableStateOf(null)
    var chatting by mutableStateOf(false)

    fun startChat(chat: Chat) {
        chatting = true
        currentChat = chat
    }

    fun endChat(): Boolean {
        if (chatting) {
            chatting = false
            return true
        }
        return false
    }

    fun boom(chat: Chat) {
        chat.msgs.add(Msg(User.Me, "\uD83D\uDCA3", "15:10").apply { read = true })
    }
}