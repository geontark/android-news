package com.example.myrealtrip

import android.app.Application
import com.example.myrealtrip.di.*
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            this@Application
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    dataSourceModule,
                    networkModule
                )
            )
        }
    }
}