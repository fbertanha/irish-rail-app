package com.felipebertanha.irishrailapp.featureforecast.data.mapper

import com.felipebertanha.irishrailapp.featureforecast.data.remote.dto.response.ArrayOfObjStationData
import com.felipebertanha.irishrailapp.featureforecast.domain.mapper.Mapper
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import javax.inject.Inject

/**
 * Created by felipebertanha on 21/October/2022
 */
class TrainMapper @Inject constructor() : Mapper<ArrayOfObjStationData, List<Train>> {
    override fun map(data: ArrayOfObjStationData): List<Train> {
        return data.objStationData.map {
            Train(
                code = it.traincode,
                destination = it.destination,
                direction = it.direction,
                expectedDeparture = it.expdepart,
                dueIn = it.duein
            )
        }
    }
}