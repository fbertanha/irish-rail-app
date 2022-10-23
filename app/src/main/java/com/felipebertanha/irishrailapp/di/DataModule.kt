package com.felipebertanha.irishrailapp.di

import com.felipebertanha.irishrailapp.featureforecast.data.mapper.TrainMapper
import com.felipebertanha.irishrailapp.featureforecast.data.remote.IrishRailServices
import com.felipebertanha.irishrailapp.featureforecast.data.repository.ForecastRepositoryImpl
import com.felipebertanha.irishrailapp.featureforecast.domain.repository.ForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by felipebertanha on 21/October/2022
 */

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesTrainMapper() = TrainMapper()

    @Singleton
    @Provides
    fun providesForecastRepository(
        irishRailServices: IrishRailServices,
        trainMapper: TrainMapper
    ): ForecastRepository = ForecastRepositoryImpl(irishRailServices, trainMapper)

}