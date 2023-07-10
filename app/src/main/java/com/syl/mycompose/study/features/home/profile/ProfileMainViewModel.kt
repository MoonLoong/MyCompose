package com.syl.mycompose.study.features.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syl.mycompose.study.constant.LocalKey
import com.syl.mycompose.study.core.base.BaseViewModel
import com.syl.mycompose.study.model.UserInfo
import com.syl.mycompose.study.repository.MyRepository
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * 个人主页的ViewModel
 */
class ProfileMainViewModel : BaseViewModel() {

    private val _userInfo = MutableLiveData<UserInfo?>()
    val userInfo: LiveData<UserInfo?> = _userInfo

    private val _menuListData = MutableLiveData(MenuListData())
    val menuListData: LiveData<MenuListData> = _menuListData

    private var currentPage: Int = 0

    suspend fun logout(): Boolean {
        return MyRepository.logout().onSuccess {
            //清理本地数据
            MMKV.defaultMMKV().remove(LocalKey.KEY_IS_LOGIN)
            MMKV.defaultMMKV().remove(LocalKey.KEY_USER_INFO)
            _userInfo.value = null
        }.onFailure {
            Timber.e(it)
        }.isSuccess
    }

    fun fetchUserInfo() {
        _userInfo.value =
            MMKV.defaultMMKV().decodeParcelable(LocalKey.KEY_USER_INFO, UserInfo::class.java)

        _menuListData.value =
            _menuListData.value!!.copy(collection = userInfo.value!!.collectIds?.size ?: 0)
    }

    /**
     * 获取自己分享的文章
     */
    fun fetchMyShare(isRefresh: Boolean) {
        if (isRefresh) {
            currentPage = 0
        }

        viewModelScope.launch {
            MyRepository.fetchMyShare(++currentPage)
                .onSuccess {
                    if (it == null) return@onSuccess
                    _menuListData.value = menuListData.value!!.copy(
                        share = it.shareArticles?.total ?: 0,
                        integral = it.coinInfo?.coinCount ?: 0
                    )
                }.onFailure {
                    //donothing
                }
        }
    }

    fun fetchMyHistory() {
        viewModelScope.launch {
            MyRepository.fetchMyHistory()
                .onSuccess {
                    _menuListData.value = menuListData.value!!.copy(history = it?.size ?: 0)
                }.onFailure {
                    //donothing
                }
        }
    }
}