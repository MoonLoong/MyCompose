package com.syl.mycompose.study.repository

import com.syl.mycompose.study.core.http.HttpResult
import com.syl.mycompose.study.model.CollectionArticles
import com.syl.mycompose.study.model.HomeList
import com.syl.mycompose.study.model.UserInfo
import com.syl.mycompose.study.model.UserShareList
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /**
     * 首页内容
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int = 0): HttpResult<HomeList?>

    /**
     * 使用密码登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun loginWithPassword(
        @Field("username") userName: String,
        @Field("password") password: String
    ): HttpResult<UserInfo?>

    /**
     * 退出登录
     */
    @GET("/user/logout/json")
    suspend fun logout(): HttpResult<Nothing?>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String,
    ): HttpResult<UserInfo?>

    /**
     * 自己的分享、积分等信息
     */
    @GET("/user/lg/private_articles/{page}/json")
    suspend fun myShare(@Path("page") page: Int): HttpResult<UserShareList?>

    /**
     * 我的收藏
     */
    @GET("lg/collect/list/{page}/json")
    suspend fun myCollection(@Path("page") page: Int): HttpResult<CollectionArticles?>
}