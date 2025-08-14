package com.example.cardapp.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
public class CardApp:Application() {
    init {
        instance = this
    }

    public companion object {
        private var instance: CardApp? = null

        public fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}