package com.kmmsampleapp

import android.app.Application
import com.shared.utils.initLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initLogger()
    }
}