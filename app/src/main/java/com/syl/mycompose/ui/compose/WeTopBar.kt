package com.syl.mycompose.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syl.mycompose.MainViewModel
import com.syl.mycompose.R
import com.syl.mycompose.ui.theme.WeComposeTheme


@Composable
fun WeTopBar(title: String, onBack: (() -> Unit)? = null) {
  Box(
    Modifier
      .background(WeComposeTheme.colors.background)
      .fillMaxWidth()
  ) {
    Row(
      Modifier
        .height(48.dp)
    ) {
      if (onBack != null) {
        Icon(
          painterResource(R.drawable.ic_back),
          null,
          Modifier
            .clickable(onClick = onBack)
            .align(Alignment.CenterVertically)
            .size(36.dp)
            .padding(8.dp),
          tint = WeComposeTheme.colors.icon
        )
      }
      Spacer(Modifier.weight(1f))
      val viewModel: MainViewModel = viewModel()
      Icon(
        painterResource(R.drawable.ic_palette),
        "切换主题",
        Modifier
          .clickable {
            viewModel.theme = when (viewModel.theme) {
              WeComposeTheme.Theme.Light -> WeComposeTheme.Theme.Dark
              WeComposeTheme.Theme.Dark -> WeComposeTheme.Theme.NewYear
              WeComposeTheme.Theme.NewYear -> WeComposeTheme.Theme.Light
            }
          }
          .align(Alignment.CenterVertically)
          .size(36.dp)
          .padding(8.dp),
        tint = WeComposeTheme.colors.icon
      )
    }
    Text(title, Modifier.align(Alignment.Center), color = WeComposeTheme.colors.textPrimary)
  }
}