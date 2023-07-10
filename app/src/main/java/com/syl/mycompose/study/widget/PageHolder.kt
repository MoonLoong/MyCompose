package com.syl.mycompose.study.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syl.mycompose.R
import com.syl.mycompose.study.core.page.PageState


/**
 * 状态页面
 */
@Composable
fun <T> PageHolder(
    pageState: PageState<T>,
    modifier: Modifier = Modifier,
    loadingComponentBlock: @Composable BoxScope.() -> Unit = {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = stringResource(id = R.string.on_loading))
        }
    },
    emptyComponentBlock: @Composable BoxScope.() -> Unit = {
        Text(
            text = stringResource(id = R.string.no_data),
            modifier = Modifier.align(Alignment.Center)
        )
    },
    errorComponentBlock: @Composable BoxScope.() -> Unit = {
        Text(
            text = stringResource(id = R.string.wrong),
            modifier = Modifier.align(Alignment.Center)
        )
    },
    comingSoonComponentBlock: @Composable BoxScope.() -> Unit = {
        Text(
            text = stringResource(id = R.string.on_developing),
            modifier = Modifier.align(Alignment.Center)
        )
    },
    content: @Composable (T?) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (pageState) {
            PageState.Loading -> loadingComponentBlock()
            is PageState.Error -> errorComponentBlock()
            is PageState.Empty -> emptyComponentBlock()
            is PageState.ComingSoon -> comingSoonComponentBlock()
            is PageState.Success<T> -> content(pageState.data)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreView() {
    PageHolder(pageState = PageState.Loading) {

    }
}

@Preview(showBackground = true)
@Composable
fun SuccessPreView() {
    PageHolder(pageState = PageState.Success("成功")) {

    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreView() {
    PageHolder(pageState = PageState.Error()) {

    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPreView() {
    PageHolder(pageState = PageState.Empty()) {

    }
}