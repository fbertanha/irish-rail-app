package com.felipebertanha.irishrailapp.featureforecast.domain.model

/**
 * Created by felipebertanha on 21/October/2022
 */
data class Train(
    val code: String,
    val destination: String,
    val direction: String,
    val expectedDeparture: String,
    val dueIn: String
)
