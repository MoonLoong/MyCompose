package com.syl.mycompose.study.repository.local

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.syl.mycompose.study.core.gson.GsonUtils
import com.syl.mycompose.study.model.Tag

class Converters {
    @TypeConverter
    fun listTags2String(tags: List<Tag>): String {
        return GsonUtils.gson.toJson(tags)
    }

    @TypeConverter
    fun string2ListTags(result: String): List<Tag> {
        return GsonUtils.gson.fromJson(result, object : TypeToken<List<Tag>>() {}.type)
    }
}