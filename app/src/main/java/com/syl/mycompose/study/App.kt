package com.syl.mycompose.study

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.github.panpf.sketch.Sketch
import com.github.panpf.sketch.SketchFactory
import com.github.panpf.sketch.decode.SvgBitmapDecoder
import com.syl.mycompose.BuildConfig
import com.syl.mycompose.study.repository.local.RoomHelp
import com.syl.mycompose.study.repository.remote.NetHelper
import com.syl.mycompose.study.log.CrashReportingTree
import com.tencent.mmkv.MMKV
import com.yariksoffice.lingver.Lingver
import com.yariksoffice.lingver.store.PreferenceLocaleStore
import timber.log.Timber

class App : Application(), SketchFactory {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appCtx = this
        //初始化日志库
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree())

        MMKV.initialize(this)

        //初始网络工具类
        NetHelper.init(this)

        //初始化数据库
        RoomHelp.init(this)

        //多语言
        val store = PreferenceLocaleStore(this)
        Lingver.init(this, store)
    }

    /**
     * Sketch支持图片加载，组件注册
     */
    override fun createSketch(): Sketch {
        return Sketch.Builder(this).apply {
            components {
                addBitmapDecoder(SvgBitmapDecoder.Factory())
            }
        }.build()
    }

    companion object {
        lateinit var appCtx: App
    }
}