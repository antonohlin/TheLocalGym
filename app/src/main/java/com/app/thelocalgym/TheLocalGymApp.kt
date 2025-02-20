package com.app.thelocalgym

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheLocalGymApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MainActivity()
    }
}