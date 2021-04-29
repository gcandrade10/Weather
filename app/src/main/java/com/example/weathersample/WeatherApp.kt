package com.example.weathersample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class WeatherApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(repositoryModule)
        }
    }
}

val repositoryModule = module {
    single { ApiFactory.api }
    viewModel { SearchViewModel(get()) }
    viewModel { ForecastViewModel(get()) }
}