package com.blackpineapple.githubrepoconsumer.application

import android.app.Application
import com.blackpineapple.githubrepoconsumer.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}