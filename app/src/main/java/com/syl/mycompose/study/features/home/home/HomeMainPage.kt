package com.syl.mycompose.study.features.home.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.syl.mycompose.study.core.page.PageState
import com.syl.mycompose.study.core.preview.ThemePreviews
import com.syl.mycompose.study.custom.MyBackHandler
import com.syl.mycompose.study.model.Article
import com.syl.mycompose.study.router.Router
import com.syl.mycompose.study.ui.common.ArticleListItemC
import com.syl.mycompose.study.widget.PageHolder
import com.syl.mycompose.study.widget.RefreshLoadMoreLazyColum
import com.syl.mycompose.study.widget.RefreshLoadMoreState


/**
 * 定义首页的 NavGraph
 */
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeMainPage(
    navController: NavHostController,
    showBottomNavigationBar: (Boolean) -> Unit
) {
    composable(Router.HomePage.route) {
        HomeMainPage { data ->
            showBottomNavigationBar(false)
            navController.navigate(Router.DetailPage.createRoute(data.toJson()))
        }
    }
}

@ThemePreviews
@Composable
fun HomeMainPage(
    modifier: Modifier = Modifier,
    viewModel: HomeMainViewModel = viewModel(),
    launchToDetailPage: ((Article) -> Unit)? = null
) {
    // liveData 转 state
    val pageState by viewModel.pageState.observeAsState(PageState.Loading)

    val tabs by viewModel.homeTab.observeAsState(emptyList())

    val selectTab by viewModel.selectTabIndex.observeAsState(0)

    val refreshLoadMoreState by viewModel.listState.observeAsState(RefreshLoadMoreState.Idle)

    MyBackHandler()

    // 外层显示状态页面
    PageHolder(
        pageState,
        modifier
    ) { data ->
        val isDark = isSystemInDarkTheme()
        Column {
            Surface(shadowElevation = 4.dp) {
                // 支持横向滑动的tab
                ScrollableTabRow(
                    selectedTabIndex = selectTab,
                    containerColor =
                    if (isDark) MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.primary,
                    edgePadding = 0.dp,
                    indicator = {
                        TabRowDefaults.Indicator(
                            Modifier
                                .tabIndicatorOffset(it[selectTab]),
                            color = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, s ->
                        Tab(
                            selected = index == selectTab,
                            selectedContentColor = if (isDark) MaterialTheme.colorScheme.onSurface
                            else MaterialTheme.colorScheme.onPrimary,
                            onClick = {
                                //重复点击同一个tab，不做任何操作
                                if (selectTab == index) {
                                    return@Tab
                                }
                                viewModel.filterListDataByTabId(tabs[index])
                                viewModel.updateSelectTabIndex(index)
                            }, text = {
                                Text(text = s)
                            })
                    }
                }
            }
            // 支持刷新和加载更多的列表
            RefreshLoadMoreLazyColum(
                modifier = Modifier.fillMaxSize(),
                state = refreshLoadMoreState,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                onRefreshCallBack = {
                    viewModel.fetchHomeList(isRefresh = true, tabName = tabs[selectTab])
                },
                loadMoreCallBack = {
                    viewModel.fetchHomeList(
                        isRefresh = false,
                        tabName = tabs[selectTab]
                    )
                },
            ) {
                itemsIndexed(
                    items = data ?: emptyList(),
                ) { _, item ->
                    ArticleListItemC(
                        article = item,
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable {
                                launchToDetailPage?.invoke(item)
                            }
                    )
                }
            }
        }
    }
}

