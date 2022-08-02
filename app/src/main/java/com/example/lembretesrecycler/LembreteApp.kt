package com.example.lembretesrecycler

import android.app.Application
import com.example.lembretesrecycler.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LembreteApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@LembreteApp)
            modules(androidModule)
        }
    }
}