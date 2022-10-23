package com.felipebertanha.irishrailapp.featureforecast.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipebertanha.irishrailapp.commom.Resource
import com.felipebertanha.irishrailapp.featureforecast.domain.usecase.GetTrainArrivalForecastBasedOnTimeUseCase
import com.felipebertanha.irishrailapp.featureforecast.presentation.uistate.ForecastUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Date
import javax.inject.Inject

/**
 * Created by felipebertanha on 21/October/2022
 */
@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getTrainArrivalForecastBasedOnTimeUseCase: GetTrainArrivalForecastBasedOnTimeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ForecastUiState>(ForecastUiState.Loading)
    val uiState: StateFlow<ForecastUiState> = _uiState
    
    fun getTrains() {
        val currentTime = Date()
        getTrainArrivalForecastBasedOnTimeUseCase(currentTime)
            .onEach {
                when (it) {
                    is Resource.Success -> _uiState.value = ForecastUiState.TrainsLoaded(it.data)
                    is Resource.Error -> _uiState.value = ForecastUiState.Error(it.exception)
                    is Resource.Loading -> _uiState.value = ForecastUiState.Loading
                }
            }
            .launchIn(viewModelScope)
    }
}