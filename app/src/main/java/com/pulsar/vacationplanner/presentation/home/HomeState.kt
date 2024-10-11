package com.pulsar.vacationplanner.presentation.home

sealed class HomeState {
    data class IsLoading(val loading: Boolean) : HomeState()
}