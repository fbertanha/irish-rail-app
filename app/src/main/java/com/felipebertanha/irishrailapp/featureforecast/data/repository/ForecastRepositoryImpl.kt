package com.felipebertanha.irishrailapp.featureforecast.data.repository

import com.felipebertanha.irishrailapp.commom.Resource
import com.felipebertanha.irishrailapp.featureforecast.data.mapper.TrainMapper
import com.felipebertanha.irishrailapp.featureforecast.data.remote.IrishRailServices
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import com.felipebertanha.irishrailapp.featureforecast.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by felipebertanha on 21/October/2022
 */
class ForecastRepositoryImpl @Inject constructor(
    private val irishRailServices: IrishRailServices,
    private val trainMapper: TrainMapper
) : ForecastRepository {
    override fun getTrainsByStationCode(stationCode: String): Flow<Resource<List<Train>>> =
        flow {
            emit(Resource.Loading)
            try {
                val trains =
                    trainMapper.map(irishRailServices.getStationDataByStationCode(stationCode))
                emit(Resource.Success((trains)))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
}