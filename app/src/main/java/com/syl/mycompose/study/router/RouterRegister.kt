package com.syl.mycompose.study.router

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.syl.mycompose.study.features.change_language.changeLanguagePage
import com.syl.mycompose.study.features.change_theme.changeThemePage
import com.syl.mycompose.study.features.collection.collectionPage
import com.syl.mycompose.study.features.details.detailPage
import com.syl.mycompose.study.features.free_style.freeStylePage
import com.syl.mycompose.study.features.home.home.homeMainPage
import com.syl.mycompose.study.features.home.profile.profileMainPage
import com.syl.mycompose.study.features.home.project.pages.coilImagePage
import com.syl.mycompose.study.features.home.project.pages.glideImagePage
import com.syl.mycompose.study.features.home.project.pages.sketchImagePage
import com.syl.mycompose.study.features.home.project.projectMainPage
import com.syl.mycompose.study.features.login.loginPage
import com.syl.mycompose.study.features.register.registerPage
import com.syl.mycompose.study.features.test_click.testClickPage
import com.syl.mycompose.study.features.webview.webviewPage

/**
 * Navigation中有三个主要的概念：
 * Navigation Graph（导航图），
 * NavHost（导航容器）
 * NavController（导航控制器）
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RouterRegister(
    navController: NavHostController,
    loginController: (Boolean, Boolean) -> Unit,
    showBottomNavigationBar: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Router.HomePage.route,// 默认首页
        modifier = modifier,
        // 进出场动画
        enterTransition = {
            if (targetState.isHomePage()) {
                fadeIn(animationSpec = tween(700))
            } else {
                slideInHorizontally(initialOffsetX = { it })
            }
        },
        exitTransition = {
            fadeOut(animationSpec = tween(700))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(700))
        },
        popExitTransition = {
            if (initialState.isHomePage()) {
                fadeOut(animationSpec = tween(700))
            } else {
                slideOutHorizontally(targetOffsetX = { it })
            }
        }
    ) {

        /**
         * 将对应逻辑抽离到对应UI文件中，保持该文件的干净，同时方便后期维护
         */

        homeMainPage(navController, showBottomNavigationBar)

        projectMainPage(navController)

        profileMainPage(navController, loginController, showBottomNavigationBar)

        glideImagePage(navController)

        coilImagePage(navController)

        sketchImagePage(navController)

        detailPage(navController, showBottomNavigationBar)

        loginPage(navController, loginController, showBottomNavigationBar)

        registerPage(navController, loginController, showBottomNavigationBar)

        freeStylePage(navController)

        changeThemePage(navController)

        changeLanguagePage(navController)

        webviewPage(navController, showBottomNavigationBar)

        collectionPage(navController, showBottomNavigationBar)

        testClickPage(navController)
    }
}

/**
 * 判断是不是回到主页面
 */
fun NavBackStackEntry.isHomePage(): Boolean {
    return destination.hierarchy.any {
        it.route == Router.HomePage.route || it.route == Router.ProjectPage.route || it.route == Router.ProfilePage.route
    }
}