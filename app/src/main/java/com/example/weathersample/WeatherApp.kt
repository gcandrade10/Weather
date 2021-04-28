package com.example.weathersample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class WeatherApp : Application(){
    override fun onCreate() {
        super.onCreate()
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
}