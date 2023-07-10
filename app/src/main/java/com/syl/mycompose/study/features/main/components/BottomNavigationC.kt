package com.syl.mycompose.study.features.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syl.mycompose.study.features.main.BottomNavigationModel
import com.syl.mycompose.study.features.main.bottomModels


@Composable
fun BottomNavigationC(
    isSelect: (BottomNavigationModel) -> Boolean,
    onClick: (BottomNavigationModel) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        bottomModels.forEach { model ->
            BottomTab(
                isSelect = isSelect(model),
                label = stringResource(id = model.labelId),
                selectIcon = model.selectIcon,
                unSelectIcon = model.unSelectIcon,
                onClick = { onClick(model) }
            )
        }
    }
}

@Composable
fun RowScope.BottomTab(
    modifier: Modifier = Modifier,
    isSelect: Boolean,
    label: String,
    selectIcon: ImageVector,
    unSelectIcon: ImageVector,
    onClick: () -> Unit
) {
    NavigationBarItem(
        selected = isSelect,
        label = {
            Text(
                text = label,
                fontWeight = if (isSelect) FontWeight.Bold else FontWeight.Normal,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }, icon = {
            Box(Modifier.padding(top = 6.dp), contentAlignment = Alignment.Center) {
                if (isSelect) {
                    Box(
                        Modifier
                            .size(width = 44.dp, height = 22.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
                    )
                }
                Icon(
                    imageVector = if (isSelect) selectIcon else unSelectIcon,
                    contentDescription = null,
                    Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        onClick = onClick,
        modifier = modifier
    )
}