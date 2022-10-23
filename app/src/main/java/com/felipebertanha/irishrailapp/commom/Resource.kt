package com.felipebertanha.irishrailapp.commom

/**
 * Created by felipebertanha on 21/October/2022
 */
sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
