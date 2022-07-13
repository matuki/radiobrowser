package br.com.matuki.radiobrowser

import android.app.Application
import timber.log.Timber

class RadioBrowserApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
