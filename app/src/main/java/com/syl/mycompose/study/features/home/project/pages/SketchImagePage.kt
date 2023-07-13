package com.syl.mycompose.study.features.home.project.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.github.panpf.sketch.compose.AsyncImage
import com.github.panpf.sketch.request.svgBackgroundColor
import com.google.accompanist.navigation.animation.composable
import com.syl.mycompose.study.constant.imageUrl
import com.syl.mycompose.study.core.preview.DevicePreviews
import com.syl.mycompose.study.core.preview.ThemePreviews
import com.syl.mycompose.study.router.Router
import com.syl.mycompose.study.ui.theme.AndroidTemplateTheme
import com.syl.mycompose.study.widget.MyTopAppBar


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.sketchImagePage(navController: NavHostController) {
    composable(Router.SketchImagePage.route) {
        SketchImagePage() {
            navController.popBackStack()
        }
    }
}

@ThemePreviews
@DevicePreviews
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SketchImagePage(clickBack: (() -> Unit)? = null) {
    AndroidTemplateTheme {
        Scaffold(topBar = {
            MyTopAppBar(
                clickBack = clickBack ?: {},
                title = "实验Sketch加载图片"
            )
        }) { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {
                Text(text = "简单加载", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                AsyncImage(
                    imageUri = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                ) {
                    crossfade()
                }

                Text(text = "加载SVG", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(12.dp))
                AsyncImage(
                    imageUri = "asset://svg/play.svg",
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)//必须设置大小，否则不显示
                ) {
                    svgBackgroundColor(Color.Green.toArgb())
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 750)
@Composable
fun PreviewSketchImagePage() {
    SketchImagePage()
}