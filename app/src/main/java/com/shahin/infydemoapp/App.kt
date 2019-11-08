package com.shahin.infydemoapp

import android.app.Application

/**
 * Created by SHAHIN 8/11/2019.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        var instance: App? = null
            private set
    }

}