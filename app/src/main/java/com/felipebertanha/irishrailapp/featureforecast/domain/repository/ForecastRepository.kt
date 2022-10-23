package com.felipebertanha.irishrailapp.featureforecast.domain.repository

import com.felipebertanha.irishrailapp.commom.Resource
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import kotlinx.coroutines.flow.Flow

/**
 * Created by felipebertanha on 21/October/2022
 */
interface ForecastRepository {

    fun getTrainsByStationCode(stationCode: String): Flow<Resource<List<Train>>>
}