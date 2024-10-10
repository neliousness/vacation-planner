package com.pulsar.vacationplanner

import android.app.Application
import com.pulsar.vacationplanner.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VacationPlannerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@VacationPlannerApplication)
            modules(appModule)
        }
    }

}