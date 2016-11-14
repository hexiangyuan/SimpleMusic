package io.github.hexiangyuan.simplemusic

import android.app.Application

/**
 * Creator:HeXiangYuan
 * Date  : 16-11-14
 */
class App : Application() {
    companion object {
        var instance: Application? = null
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}