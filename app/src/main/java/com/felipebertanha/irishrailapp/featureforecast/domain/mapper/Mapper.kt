package com.felipebertanha.irishrailapp.featureforecast.domain.mapper

/**
 * Created by felipebertanha on 21/October/2022
 */
interface Mapper<FROM, TO> {

    fun map(data: FROM): TO
}