package com.syl.mycompose.study.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.syl.mycompose.study.model.Article

@Dao
interface ArticleDao {
    /**
     * 查询所有阅读过的文章
     */
    @Query("SELECT * FROM t_article")
    suspend fun fetchAll(): List<Article>

    /**
     * 插入阅读过的文章
     */
    @Insert
    suspend fun insertAll(vararg articles: Article)

    /**
     * 删除某篇阅读记录
     */
    @Delete
    suspend fun delete(article: Article)
}