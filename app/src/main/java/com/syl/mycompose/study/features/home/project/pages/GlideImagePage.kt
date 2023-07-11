package com.syl.mycompose.study.features.home.project.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.syl.mycompose.study.core.preview.DevicePreviews
import com.syl.mycompose.study.core.preview.ThemePreviews
import com.syl.mycompose.study.router.Router
import com.syl.mycompose.study.ui.theme.AndroidTemplateTheme
import com.syl.mycompose.study.widget.GlideImage
import com.syl.mycompose.study.widget.MyTopAppBar


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.glideImagePage(navController: NavHostController) {
    composable(Router.GlideImagePage.route) {
        GlideImagePage {
            navController.popBackStack()
        }
    }
}


/**
 * 测试图片地址
 */
const val imageUrl =
    "https://img1.baidu.com/it/u=3009731526,373851691&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500"

@ThemePreviews
@DevicePreviews
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlideImagePage(clickBack: (() -> Unit)? = null) {
    AndroidTemplateTheme {
        Scaffold(topBar = {
            MyTopAppBar(title = "实验Glide加载图片功能", clickBack = clickBack ?: {})
        }) { paddingValue ->
            val density = LocalDensity.current

            Column(
                Modifier
                    .padding(paddingValue)
                    .padding(16.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {

                Text(text = "简单加载", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(model = imageUrl)

                Divider(Modifier.padding(vertical = 8.dp))

                Text(text = "固定大小-Fit", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(
                    model = imageUrl,
                    Modifier
                        .background(Color.Green)
                        .size(80.dp, 60.dp),
                    contentScale = ContentScale.Fit
                )

                Divider(Modifier.padding(vertical = 8.dp))

                Text(text = "固定大小-Crop", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(
                    model = imageUrl,
                    Modifier
                        .background(Color.Green)
                        .size(80.dp, 60.dp),
                    contentScale = ContentScale.Crop
                )

                Divider(Modifier.padding(vertical = 8.dp))

                Text(text = "固定大小-Inside", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(
                    model = imageUrl,
                    Modifier
                        .background(Color.Green)
                        .size(80.dp, 60.dp),
                    contentScale = ContentScale.Inside
                )

                Divider(Modifier.padding(vertical = 8.dp))

                Text(text = "固定大小-FillBounds", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(
                    model = imageUrl,
                    Modifier
                        .background(Color.Green)
                        .size(80.dp, 60.dp),
                    contentScale = ContentScale.FillBounds
                )

                Divider(Modifier.padding(vertical = 8.dp))

                Text(text = "固定大小-FillHeight", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(
                    model = imageUrl,
                    Modifier
                        .background(Color.Green)
                        .size(80.dp, 60.dp),
                    contentScale = ContentScale.FillHeight
                )

                Divider(Modifier.padding(vertical = 8.dp))

                Text(text = "固定大小-FillWidth", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                GlideImage(
                    model = imageUrl,
                    Modifier
                        .background(Color.Green)
                        .size(80.dp, 60.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}