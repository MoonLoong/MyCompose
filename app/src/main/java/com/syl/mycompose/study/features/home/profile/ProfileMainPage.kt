package com.syl.mycompose.study.features.home.profile

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.syl.mycompose.study.features.home.profile.components.LoginWarningDialogC
import com.syl.mycompose.study.features.home.profile.components.LogoutDialogC
import com.syl.mycompose.study.features.home.profile.components.ProfileAvatarC
import com.syl.mycompose.study.features.home.profile.components.ProfileMenuItemC
import com.syl.mycompose.study.features.home.profile.components.ProfileUserNameC
import com.syl.mycompose.study.ui.common.OnDevelopingDialogC
import com.syl.mycompose.R
import com.syl.mycompose.study.App
import com.syl.mycompose.study.constant.HttpUrl
import com.syl.mycompose.study.custom.MyBackHandler
import com.syl.mycompose.study.features.main.LocalLoginState
import com.syl.mycompose.study.router.Router
import kotlinx.coroutines.launch


internal val CollapsedAppBarHeight = 56.dp
internal val ExpandedAppBarHeight = 270.dp
internal val ExpandedImageSize = 80.dp
internal val CollapsedImageSize = 36.dp
internal val ExpandedImageOffsetY = CollapsedAppBarHeight
internal val CollapsedImageOffsetY = (CollapsedAppBarHeight - CollapsedImageSize) / 2
internal val ExpandedImageOffsetX = 24.dp
internal val CollapsedImageOffsetX = 16.dp
internal val CollapsedOffsetY =
    ExpandedAppBarHeight - ExpandedImageOffsetY - ExpandedImageSize - 32.dp

internal val ExpandedTextLineHeight = 36.sp
internal val CollapsedTextSizeLineHeight = 20.sp
internal val ExpandedTextSize = 28.sp
internal val CollapsedTextSize = 16.sp

internal val ExpandedTextOffsetX = ExpandedImageOffsetX + ExpandedImageSize + ExpandedImageOffsetX
internal val CollapsedTextOffsetX =
    CollapsedImageOffsetX + CollapsedImageSize + 12.dp
internal val ExpandedTextOffsetY = ExpandedImageOffsetY + ExpandedImageSize / 2 - 36.dp / 2
internal val CollapsedTextOffsetY = CollapsedAppBarHeight / 2 - 20.dp / 2


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileMainPage(
    navController: NavHostController,
    loginController: (Boolean, Boolean) -> Unit,
    showBottomNavigationBar: (Boolean) -> Unit
) {
    composable(Router.ProfilePage.route) {
        ProfileMainPage(launchLoginPage = {
            showBottomNavigationBar(false)
            navController.navigate(Router.LoginPage.route)
        }, loginController = loginController,
            launchChangeLanguagePage = {
                navController.navigate(Router.ChangeLanguagePage.route)
            }, launchChangeThemePage = {
                navController.navigate(Router.ChangeThemePage.route)
            }, launchOpenSourcePage = {
//                showBottomNavigationBar(false)
//                navController.navigate(
//                    Router.MarkdownPreviewPage.createRoute(
//                        MarkdownPreviewPageArgs(
//                            "markdown/OpenSourceLibs.md",
//                            R.string.use_open_source_libs
//                        ).toJson()
//                    )
//                )
            }, launchProjectDoc = {
//                showBottomNavigationBar(false)
//                navController.navigate(
//                    Router.MarkdownPreviewPage.createRoute(
//                        MarkdownPreviewPageArgs(
//                            "markdown/ProjectDoc.md",
//                            R.string.this_project_docs
//                        ).toJson()
//                    )
//                )
            }, launchTodo = {
//                showBottomNavigationBar(false)
//                navController.navigate(
//                    Router.MarkdownPreviewPage.createRoute(
//                        MarkdownPreviewPageArgs(
//                            "markdown/ToDo.md",
//                            R.string.todo
//                        ).toJson()
//                    )
//                )
            }, launchWebPage = { url ->
                showBottomNavigationBar(false)
                navController.navigate(Router.WebViewPage.createRoute(url))
            }, launchCollectionPage = {
                showBottomNavigationBar(false)
                navController.navigate(Router.CollectionPage.route)
            }, launchSharePage = {

            }, launchIntegralPage = {

            }, launchHistoryPage = {

            }, launchMessagePage = {

            })
    }
}

@Composable
fun ProfileMainPage(
    modifier: Modifier = Modifier,
    viewModel: ProfileMainViewModel = viewModel(),
    launchLoginPage: () -> Unit,
    loginController: (Boolean, Boolean) -> Unit,
    launchChangeLanguagePage: () -> Unit,
    launchChangeThemePage: () -> Unit,
    launchOpenSourcePage: () -> Unit,
    launchProjectDoc: () -> Unit,
    launchTodo: () -> Unit,
    launchWebPage: (String) -> Unit,
    launchCollectionPage: () -> Unit,
    launchSharePage: () -> Unit,
    launchIntegralPage: () -> Unit,
    launchHistoryPage: () -> Unit,
    launchMessagePage: () -> Unit,
) {
    MyBackHandler()


    var showLogoutDialog by rememberSaveable { mutableStateOf(false) }
    if (showLogoutDialog) {
        val scope = rememberCoroutineScope()
        LogoutDialogC(onDismiss = { showLogoutDialog = false }) {
            scope.launch {
                if (viewModel.logout()) {
                    showLogoutDialog = false
                    loginController(false, true)
                }
            }
        }
    }

    var showLoginWarningDialog by rememberSaveable { mutableStateOf(false) }
    if (showLoginWarningDialog) {
        LoginWarningDialogC(onDismiss = { showLoginWarningDialog = false }) {
            showLoginWarningDialog = false
            launchLoginPage()
        }
    }


    var showOnDevelopDialog by rememberSaveable { mutableStateOf(false) }
    if (showOnDevelopDialog) {
        OnDevelopingDialogC { showOnDevelopDialog = false }
    }

    val userInfo by viewModel.userInfo.observeAsState(null)

    val loginState = LocalLoginState.current
    if (loginState) {
        /**
         * key为Unit或true这样的常量，则block只在OnActive时执行一次。即登录后只请求一次
         * 如果key为其他变量，则block在OnActive以及参数变化时的OnUpdate中执行，
         * 如果key变化时，block再次执行。
         */
        DisposableEffect(Unit) {
            viewModel.fetchUserInfo()
            viewModel.fetchMyShare(true)
            viewModel.fetchMyHistory()
            onDispose { }
        }
    }

    val menuListData by viewModel.menuListData.observeAsState(MenuListData())

    Box(modifier.fillMaxSize()) {

        val scroll = rememberScrollState(0)

        //渐变背景色
        GradientHeader()

        //用户积分等信息
        BaseUserInfo(
            if (userInfo == null) menuListData.copy(
                collection = 0,
                share = 0,
                integral = 0,
                history = 0
            ) else menuListData,
            launchCollectionPage = launchCollectionPage,
            launchSharePage = launchSharePage,
            launchIntegralPage = launchIntegralPage,
            launchHistoryPage = launchHistoryPage
        )

        //通知小铃铛
        Notification(launchMessagePage = launchMessagePage)

        //菜单
        MenuList(
            scroll,
            launchWebPage = launchWebPage,
            exitLogin = {
                showLogoutDialog = true
            },
            checkUpdate = {
                if (!loginState) {
                    showLoginWarningDialog = true
                } else {
                    // 弹框提示开发中
                    showOnDevelopDialog = true
                }
            },
            launchChangeLanguagePage = launchChangeLanguagePage,
            launchChangeThemePage = launchChangeThemePage,
            launchOpenSourcePage = launchOpenSourcePage,
            launchProjectDoc = launchProjectDoc,
            launchTodo = launchTodo,
        )

        //头像
        ProfileAvatarC(
            modifier = Modifier
                .then(
                    if (!LocalLoginState.current)
                        Modifier.clickable(onClick = launchLoginPage,
                            indication = null,//禁止水波纹
                            interactionSource = remember { MutableInteractionSource() })
                    else Modifier
                )
        ) { scroll.value }

        //用户名
        ProfileUserNameC(
            if (userInfo == null) stringResource(id = R.string.sign_in_up)
            else userInfo!!.nickname ?: ""
        ) { scroll.value }
    }
}

@Composable
fun BoxScope.Notification(launchMessagePage: () -> Unit) {
    Box(
        modifier = Modifier
            .height(CollapsedAppBarHeight)
            .align(Alignment.TopEnd)
    ) {
        IconButton(onClick = launchMessagePage, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
        }
    }
}


@Composable
fun GradientHeader() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(ExpandedAppBarHeight)
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            )
    )
}

@Composable
fun BaseUserInfo(
    data: MenuListData,
    launchCollectionPage: () -> Unit,
    launchSharePage: () -> Unit,
    launchIntegralPage: () -> Unit,
    launchHistoryPage: () -> Unit
) {

    Card(
        modifier = Modifier.padding(
            top = ExpandedImageOffsetY + ExpandedImageSize + 16.dp,
            start = 24.dp,
            end = 24.dp
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Row(Modifier.height(80.dp)) {
            BaseUserInfoItem(
                labelId = R.string.collection,
                value = data.collection.toString(),
                onClick = launchCollectionPage
            )
            BaseUserInfoItem(
                labelId = R.string.share,
                value = data.share.toString(),
                onClick = launchSharePage
            )
            BaseUserInfoItem(
                labelId = R.string.rewards_points,
                value = data.integral.toString(),
                onClick = launchIntegralPage
            )
            BaseUserInfoItem(
                labelId = R.string.history,
                value = data.history.toString(),
                onClick = launchHistoryPage
            )
        }
    }
}

@Composable
fun RowScope.BaseUserInfoItem(@StringRes labelId: Int, value: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(6.dp)
            .clickable(onClick = onClick)
            .fillMaxHeight()
            .weight(1.0f)
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = labelId), maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}


@Composable
fun MenuList(
    scroll: ScrollState,
    launchWebPage: (String) -> Unit,
    exitLogin: () -> Unit,
    checkUpdate: () -> Unit,
    launchChangeLanguagePage: () -> Unit,
    launchChangeThemePage: () -> Unit,
    launchOpenSourcePage: () -> Unit,
    launchProjectDoc: () -> Unit,
    launchTodo: () -> Unit,
) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(CollapsedAppBarHeight)
        )
        Column(modifier = Modifier.verticalScroll(scroll)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ExpandedAppBarHeight - CollapsedAppBarHeight - 20.dp)
            )


            Surface(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Column(Modifier.fillMaxWidth()) {
                    MenuNormal(
                        launchOpenSourcePage = launchOpenSourcePage,
                        launchProjectDoc = launchProjectDoc,
                        launchTodo = launchTodo
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    MenuSetup(launchChangeLanguagePage, launchChangeThemePage)
                    Spacer(modifier = Modifier.height(8.dp))
                    MenuAccount(launchWebPage, exitLogin, checkUpdate)
                    //为了演示滑动效果，故意增高
                    Spacer(modifier = Modifier.height(200.dp))
                }
            }
        }
    }
}

@Composable
fun MenuNormal(
    launchOpenSourcePage: () -> Unit,
    launchProjectDoc: () -> Unit,
    launchTodo: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.menu_normal),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card {
            ProfileMenuItemC(
                iconRes = R.drawable.icon_004,
                title = R.string.open_source_libraries,
                onClick = launchOpenSourcePage
            )
            ProfileMenuItemC(
                iconRes = R.drawable.icon_005,
                title = R.string.this_project_docs,
                onClick = launchProjectDoc
            )
            ProfileMenuItemC(
                iconRes = R.drawable.icon_006,
                title = R.string.todo,
                onClick = launchTodo
            )
        }
    }
}

@Composable
fun MenuSetup(launchChangeLanguagePage: () -> Unit, launchChangeThemePage: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.menu_settings),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card {
            ProfileMenuItemC(iconRes = R.drawable.icon_001, title = R.string.change_language) {
                launchChangeLanguagePage()
            }
            ProfileMenuItemC(iconRes = R.drawable.icon_002, title = R.string.change_theme) {
                launchChangeThemePage()
            }
        }
    }
}

@Composable
fun MenuAccount(launchWebPage: (String) -> Unit, exitLogin: () -> Unit, checkUpdate: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.menu_account),
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card {
            ProfileMenuItemC(
                iconRes = R.drawable.icon_001,
                title = R.string.privacy_policy,
                onClick = { launchWebPage(HttpUrl.Privacy_Policy) }
            )
            ProfileMenuItemC(
                iconRes = R.drawable.icon_002,
                title = R.string.user_service,
                onClick = { launchWebPage(HttpUrl.User_Policy) }
            )
            if (LocalLoginState.current) {
                ProfileMenuItemC(
                    iconRes = R.drawable.icon_001,
                    title = R.string.sign_out,
                    onClick = exitLogin
                )
            }
            ProfileMenuItemC(
                iconRes = R.drawable.icon_002,
                title = R.string.check_update,
                onClick = checkUpdate
            )
        }
    }
}

data class MenuListData(
    val collection: Int = 0,//收藏
    val share: Int = 0,//分享
    val integral: Int = 0,//积分
    val history: Int = 0,//历史
)
