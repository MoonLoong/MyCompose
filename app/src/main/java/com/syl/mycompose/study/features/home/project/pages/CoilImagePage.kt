package com.syl.mycompose.study.features.home.project.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.navigation.animation.composable
import com.syl.mycompose.study.constant.imageUrl
import com.syl.mycompose.study.core.preview.DevicePreviews
import com.syl.mycompose.study.core.preview.ThemePreviews
import com.syl.mycompose.study.router.Router
import com.syl.mycompose.study.ui.theme.AndroidTemplateTheme
import com.syl.mycompose.study.widget.MyTopAppBar


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.coilImagePage(navController: NavHostController) {
    composable(Router.CoilImagePage.route) {
        CoilImagePage() {
            navController.popBackStack()
        }
    }
}


/**
 * Coil 名字的由来：取 Coroutine Image Loader 首字母得来。
 *
 * 更快、更轻、更容易使用
 */
@ThemePreviews
@DevicePreviews
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoilImagePage(clickBack: (() -> Unit)? = null) {
    AndroidTemplateTheme {
        Scaffold(topBar = {
            MyTopAppBar(
                title = "实验Coil加载图片功能",
                clickBack = clickBack ?: {})
        }) { paddingValue ->
            Column(Modifier.padding(paddingValue)) {

                Text(
                    text = "简单加载",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(
                    modifier = Modifier.height(12.dp)
                )
                /**
                 * 异步加载图片
                 * 内部使用了 rememberAsyncImagePainter
                 */
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null
                )


                Divider(Modifier.padding(vertical = 8.dp))

                Text(
                    text = "监听状态",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(12.dp))

                /**
                 * 返回一个异步ImagePainter，该程序异步执行ImageRequest并呈现结果。
                 * 这是一个比AsyncImage更低级别的API，可能无法在所有情况下都按预期工作
                 */
                val painter = rememberAsyncImagePainter(imageUrl)

                Box {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        Modifier.size(200.dp)
                    )
                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            // 显示一个加载中的进度条
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }

                        is AsyncImagePainter.State.Error -> {
                            // 如果发生了什么错误，你可以在这里写
                            Text(
                                text = "发生错误",
//                                color = TextColorRed
                            )
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }
}