package com.pulsar.vacationplanner

import android.app.Application
import com.pulsar.vacationplanner.di.networkModule
import com.pulsar.vacationplanner.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VacationPlannerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@VacationPlannerApplication)
            modules(presentationModule, networkModule)
        }
    }

}