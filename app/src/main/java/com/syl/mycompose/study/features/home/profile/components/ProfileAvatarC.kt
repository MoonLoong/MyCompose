package com.syl.mycompose.study.features.home.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import com.syl.mycompose.study.features.home.profile.CollapsedImageOffsetX
import com.syl.mycompose.study.features.home.profile.CollapsedImageOffsetY
import com.syl.mycompose.study.features.home.profile.CollapsedImageSize
import com.syl.mycompose.study.features.home.profile.CollapsedOffsetY
import com.syl.mycompose.study.features.home.profile.ExpandedImageOffsetX
import com.syl.mycompose.study.features.home.profile.ExpandedImageOffsetY
import com.syl.mycompose.study.features.home.profile.ExpandedImageSize
import com.syl.mycompose.R
import kotlin.math.max
import kotlin.math.min


/**
 * 个人主页头像
 */
@Composable
fun ProfileAvatarC(modifier: Modifier = Modifier, scrollProvider: () -> Int) {
    val collapseRange = with(LocalDensity.current) { ExpandedImageOffsetY.toPx() }
    //开始收缩的距离差值
    val offset = with(LocalDensity.current) { CollapsedOffsetY.toPx() }
    val collapseFractionProvider =
        { ((scrollProvider() - offset) / collapseRange).coerceIn(0f, 1f) }

    CollapsingAvatarLayout(
        modifier = modifier,
        collapseFractionProvider = collapseFractionProvider
    ) {
        Surface(
            shadowElevation = 8.dp,
            shape = CircleShape,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar_3),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
    }
}

/**
 * 自定义折叠头像布局
 */
@Composable
private fun CollapsingAvatarLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(ExpandedImageOffsetY, CollapsedImageOffsetY, collapseFraction).roundToPx()
        val imageX = lerp(ExpandedImageOffsetX, CollapsedImageOffsetX, collapseFraction).roundToPx()
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}