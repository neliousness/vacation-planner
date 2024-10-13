package com.pulsar.vacationplanner.domain.usecases

import com.pulsar.vacationplanner.util.Constants.ONBOARDED
import com.pulsar.vacationplanner.util.PreferencesHelper

class LoadOnboardDataUseCase(private val helper: PreferencesHelper) {
    operator fun invoke(): Boolean {
        return helper.getBoolean(ONBOARDED, false)
    }
}