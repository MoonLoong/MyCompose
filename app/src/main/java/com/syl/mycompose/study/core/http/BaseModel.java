package com.syl.mycompose.study.core.http;

/**
 * 接口数据基本Model
 * @param <T>
 */
public interface BaseModel<T> {
    boolean isSuccess();

    int getCode();

    String getMessage();

    T getData();
}
