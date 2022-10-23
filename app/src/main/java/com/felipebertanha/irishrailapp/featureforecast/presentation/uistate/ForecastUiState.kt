package com.felipebertanha.irishrailapp.featureforecast.presentation.uistate

import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train

sealed class ForecastUiState {

    data class TrainsLoaded(
        val trains: List<Train>
    ) : ForecastUiState()

    data class Error(val throwable: Throwable) : ForecastUiState()
    object Loading : ForecastUiState()
}