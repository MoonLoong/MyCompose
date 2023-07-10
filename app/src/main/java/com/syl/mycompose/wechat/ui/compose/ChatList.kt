package com.syl.mycompose.wechat.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syl.mycompose.wechat.MainViewModel
import com.syl.mycompose.R
import com.syl.mycompose.wechat.data.Chat
import com.syl.mycompose.theme.WeComposeTheme

@Composable
fun ChatList(chats: List<Chat>) {
    // ListView
    // RecyclerView
    // onDraw()
    Column(
        Modifier
            .background(WeComposeTheme.colors.background)
            .fillMaxSize()
    ) {
        ChatListTopBar()
        LazyColumn(Modifier.background(WeComposeTheme.colors.listItem)) {
            itemsIndexed(chats) { index, chat ->
                ChatListItem(chat)
                if (index < chats.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(start = 68.dp),
                        color = WeComposeTheme.colors.chatListDivider,
                        thickness = 0.8f.dp
                    )
                }
            }
        }
    }
}

@Composable
fun ChatListTopBar() {
    WeTopBar(title = stringResource(id = R.string.title_we_chat))
}

@Composable
private fun ChatListItem(chat: Chat) {
    val viewModel: MainViewModel = viewModel()
    Row(
        Modifier
            .clickable {
                viewModel.startChat(chat)
            }
            .fillMaxWidth()
    ) {
        Image(
            painterResource(chat.friend.avatar), chat.friend.name,
            Modifier
                .padding(8.dp)
                .size(48.dp)
                .unread(!chat.msgs.last().read, WeComposeTheme.colors.badge)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(chat.friend.name, fontSize = 17.sp, color = WeComposeTheme.colors.textPrimary)
            Text(
                chat.msgs.last().text,
                fontSize = 14.sp,
                color = WeComposeTheme.colors.textSecondary
            )
        }
        Text(
            chat.msgs.last().time,
            Modifier.padding(8.dp, 8.dp, 12.dp, 8.dp),
            fontSize = 11.sp, color = WeComposeTheme.colors.textSecondary
        )
    }
}

fun Modifier.unread(show: Boolean, color: Color): Modifier = this.drawWithContent {
    drawContent()
    if (show) {
        drawCircle(color, 5.dp.toPx(), Offset(size.width - 1.dp.toPx(), 1.dp.toPx()))
    }
}