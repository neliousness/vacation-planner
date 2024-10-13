package com.pulsar.vacationplanner.domain.usecases

import com.pulsar.vacationplanner.util.PreferencesHelper

class SaveOnboardDataUseCase(private val helper: PreferencesHelper) {
    operator fun invoke(key: String, value: Boolean) {
        return helper.saveBoolean(key, value)
    }
}