package com.syl.mycompose.study.router

/**
 * 所有页面的路由定义
 * 使用步骤：
 * 1. 在 Router 类中集中管理路由地址
 * 2. 页面中定义 NavGraphBuilder 的扩展方法，声明路由
 * 3. 在 AnimatedNavHost 中注册路由
 * 4. 使用 navController.navigate(Router.HomePage.route) 进行跳转
 *
 * 如果需要传参，请参照DetailPage，单独提供方法，统一传参，方便维护
 */
sealed class Router(val route: String) {
    object HomePage : Router("home")
    object ProjectPage : Router("project")
    object ProfilePage : Router("profile")

    object GlideImagePage : Router("glide/image")
    object CoilImagePage : Router("coil/image")
    object SketchImagePage : Router("sketch/image")

    object DetailPage : Router("detail?{args}") {
        fun createRoute(args: String): String = "detail?$args"
    }

    object LoginPage : Router("login")
    object RegisterPage : Router("register")
    object FreeStylePage : Router("free/style")
    object ChangeThemePage : Router("change/theme")
    object ChangeLanguagePage : Router("change/language")

    object WebViewPage : Router("webview?{url}") {
        fun createRoute(url: String): String = "webview?$url"
    }

    object CollectionPage : Router("collection")
    object TestClickPage : Router("test/click")
}
