package com.felipebertanha.irishrailapp.featureforecast.data.remote

import com.felipebertanha.irishrailapp.featureforecast.data.remote.dto.response.ArrayOfObjStationData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by felipebertanha on 21/October/2022
 */
interface IrishRailServices {

    @GET("/realtime/realtime.asmx/getStationDataByCodeXML")
    suspend fun getStationDataByStationCode(
        @Query("StationCode") stationCode: String
    ): ArrayOfObjStationData
}