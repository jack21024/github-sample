package com.jack.sample.github

import android.app.Application
import timber.log.Timber

class GsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}