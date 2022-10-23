package com.felipebertanha.irishrailapp.featureforecast.domain.usecase

import com.felipebertanha.irishrailapp.commom.Resource
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import com.felipebertanha.irishrailapp.featureforecast.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

/**
 * Created by felipebertanha on 21/October/2022
 */
class GetTrainArrivalForecastBasedOnTimeUseCase @Inject constructor(
    private val repository: ForecastRepository
) {

    /**
     * Given I am a Irish rail passenger
     * When I open the app from 00:00 – 12:00
     * Then I should see train forecast from Pearse street train stop towards Northbound
     * ---
     * Given I am a Irish rail passenger
     * When I open the app from 12:01 – 23:59
     * Then I should see trams forecast from Howth train stop towards Southbound
     * */

    operator fun invoke(currentTime: Date): Flow<Resource<List<Train>>> {
        if (currentTime.before(getHowthTimeCondition())) {
            return repository.getTrainsByStationCode(PEARSE_STATION_CODE)
                .map { filterTrainsByDestination(it, NORTHBOUND_DIRECTION) }
        }
        return repository.getTrainsByStationCode(HOWTHO_STATION_CODE)
            .map { filterTrainsByDestination(it, SOUTHBOUND_DIRECTION) }
    }

    private fun filterTrainsByDestination(it: Resource<List<Train>>, destinationName: String) =
        when (it) {
            is Resource.Success -> {
                Resource.Success(
                    it.data.filter { train ->
                        train.direction == destinationName
                    }
                )
            }
            is Resource.Loading -> Resource.Loading
            is Resource.Error -> Resource.Error(it.exception)
        }

    private fun getHowthTimeCondition() = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 12)
        set(Calendar.MINUTE, 1)
        set(Calendar.SECOND, 0)
    }.time

    companion object {
        private const val NORTHBOUND_DIRECTION = "Northbound"
        private const val SOUTHBOUND_DIRECTION = "Southbound"
        private const val PEARSE_STATION_CODE = "perse"
        private const val HOWTHO_STATION_CODE = "howth"
    }

}