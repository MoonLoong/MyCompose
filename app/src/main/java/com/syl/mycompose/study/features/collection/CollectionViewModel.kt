package com.syl.mycompose.study.features.collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syl.mycompose.study.core.base.BaseViewModel
import com.syl.mycompose.study.core.exception.Failure
import com.syl.mycompose.study.model.Article
import com.syl.mycompose.study.repository.MyRepository
import com.syl.mycompose.study.widget.RefreshLoadMoreState
import kotlinx.coroutines.launch


/**
 * 收藏的ViewModel
 */
class CollectionViewModel : BaseViewModel() {
    private val _listState = MutableLiveData<RefreshLoadMoreState>()
    val listState: LiveData<RefreshLoadMoreState> = _listState

    private val _listData = MutableLiveData<List<Article>>(emptyList())
    val listData: LiveData<List<Article>> = _listData

    private var currentPage: Int = -1


    init {
        fetchMyCollection(true)
    }

    fun fetchMyCollection(isRefresh: Boolean) {
        if (isRefresh) currentPage = -1
        viewModelScope.launch {
            MyRepository.fetchMyCollection(++currentPage)
                .onSuccess {
                    if (it == null) {
                        _listState.value = RefreshLoadMoreState.NoData
                    }
                }
                .onFailure {
                    if (isRefresh) {
                        when (it) {
                            is Failure.NetworkError -> _listState.value =
                                RefreshLoadMoreState.Error(
                                    Throwable("没有网络呀"), "去开启网络"
                                )

                            is Failure.ServerError -> _listState.value =
                                RefreshLoadMoreState.Error(
                                    Throwable("${it.msg}:${it.code}")
                                )

                            is Failure.EmptyData -> _listState.value =
                                RefreshLoadMoreState.NoData

                            is Failure.OtherError -> _listState.value =
                                RefreshLoadMoreState.Error(it)
                        }
                    } else {
                        when (it) {
                            is Failure.NetworkError -> _listState.value =
                                RefreshLoadMoreState.LoadMoreError(Throwable("没有网络呀"))

                            is Failure.ServerError -> _listState.value =
                                RefreshLoadMoreState.LoadMoreError(
                                    Throwable("${it.msg}:${it.code}")
                                )

                            is Failure.EmptyData -> _listState.value =
                                RefreshLoadMoreState.NoMore

                            is Failure.OtherError -> _listState.value =
                                RefreshLoadMoreState.LoadMoreError(it)
                        }
                    }
                }
        }
    }

    /**
     * 移除对应数据
     */
    fun afterRemoveUpdateListData(byRemove: Article) {
        val newData = _listData.value!!.toMutableList()
        newData.remove(byRemove)
        _listData.value = newData
    }
}