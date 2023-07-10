package com.syl.mycompose.study.repository

import com.syl.mycompose.study.repository.remote.NetHelper
import com.syl.mycompose.study.core.http.HttpResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import com.syl.mycompose.study.core.exception.Failure
import com.syl.mycompose.study.repository.local.RoomHelp
import com.syl.mycompose.study.model.Article
import com.syl.mycompose.study.model.CollectionArticles
import com.syl.mycompose.study.model.HomeList
import com.syl.mycompose.study.model.UserInfo
import com.syl.mycompose.study.model.UserShareList
import timber.log.Timber

object MyRepository {

    suspend fun fetchHomeList(page: Int): Result<HomeList?> {
        return handleException { NetHelper.service.getHomeList(page) }
    }

    suspend fun loginWithPassword(username: String, password: String): Result<UserInfo?> {
        return handleException { NetHelper.service.loginWithPassword(username, password) }
    }

    suspend fun logout(): Result<Nothing?> {
        return handleException { NetHelper.service.logout() }
    }

    suspend fun register(
        username: String,
        password: String,
        rePassword: String
    ): Result<UserInfo?> {
        return handleException { NetHelper.service.register(username, password, rePassword) }
    }

    suspend fun fetchMyShare(page: Int = 1): Result<UserShareList?> {
        return handleException { NetHelper.service.myShare(page) }
    }

    suspend fun fetchMyHistory(): Result<List<Article>?> {
        return try {
            Result.success(RoomHelp.db.articleDao().fetchAll())
        } catch (e: Throwable) {
            Result.failure(Failure.OtherError(e))
        }
    }

    suspend fun fetchMyCollection(page: Int = 0): Result<CollectionArticles?> {
        return handleException { NetHelper.service.myCollection(page) }
    }

    /**
     * 异常处理，减少模板代码
     */
    @OptIn(ExperimentalContracts::class)
    private suspend inline fun <T> handleException(crossinline onSuccess: suspend () -> HttpResult<T?>): Result<T?> {
        contract {
            callsInPlace(onSuccess, InvocationKind.AT_MOST_ONCE)
        }
        return if (NetHelper.networkHandler.isNetworkAvailable()) {
            try {
                val httpResult = onSuccess()
                if (httpResult.isSuccess) {
                    Result.success(httpResult.data)
                } else {
                    Timber.d("错误信息：${httpResult.code},${httpResult.message}")
                    Result.failure(
                        Failure.ServerError(
                            code = httpResult.code,
                            msg = httpResult.message
                        )
                    )
                }
            } catch (e: Throwable) {
                //HttpException等错误会走这里
                Result.failure(Failure.OtherError(e))
            }
        } else {
            Result.failure(Failure.NetworkError)
        }
    }
}