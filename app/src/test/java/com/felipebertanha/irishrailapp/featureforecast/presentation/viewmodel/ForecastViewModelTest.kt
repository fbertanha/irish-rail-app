package com.felipebertanha.irishrailapp.featureforecast.presentation.viewmodel

import app.cash.turbine.test
import com.MainDispatcherRule
import com.felipebertanha.irishrailapp.commom.Resource
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import com.felipebertanha.irishrailapp.featureforecast.domain.usecase.GetTrainArrivalForecastBasedOnTimeUseCase
import com.felipebertanha.irishrailapp.featureforecast.presentation.uistate.ForecastUiState
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by felipebertanha on 23/October/2022
 */
@ExperimentalCoroutinesApi
class ForecastViewModelTest {
    // Necessary to override the Dispatchers.Main in coroutines
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getTrainArrivalForecastBasedOnTimeUseCase: GetTrainArrivalForecastBasedOnTimeUseCase
    private lateinit var viewModel: ForecastViewModel

    @Before
    fun setup() {
        getTrainArrivalForecastBasedOnTimeUseCase =
            mockk<GetTrainArrivalForecastBasedOnTimeUseCase>()
        viewModel = ForecastViewModel(getTrainArrivalForecastBasedOnTimeUseCase)
    }

    @Test
    fun `Given normal conditions When I request trains forecast Then I should see forecast successfully`() =
        runTest {
            coEvery {
                getTrainArrivalForecastBasedOnTimeUseCase(any())
            } returns flow {
                emit(Resource.Loading)
                emit(
                    Resource.Success(
                        listOf(
                            Train(
                                code = "01",
                                destination = "destination 1",
                                direction = "Northbound",
                                expectedDeparture = "12:01",
                                dueIn = "1"
                            ),
                            Train(
                                code = "02",
                                destination = "destination 2",
                                direction = "Southbound",
                                expectedDeparture = "12:02",
                                dueIn = "2"
                            )
                        )
                    )
                )
            }

            viewModel.uiState.test {
                viewModel.getTrains()
                assertThat(awaitItem() is ForecastUiState.Loading).isTrue()
                assertThat(awaitItem() is ForecastUiState.TrainsLoaded).isTrue()
            }
        }

    @Test
    fun `Given the irish rail service is unavailable When I request trains forecast Then I should see an error message`() =
        runTest {
            coEvery {
                getTrainArrivalForecastBasedOnTimeUseCase(any())
            } returns flow {
                emit(Resource.Loading)
                emit(Resource.Error(Exception("Unable to reach Irish Rail Services")))
            }

            viewModel.uiState.test {
                viewModel.getTrains()
                assertThat(awaitItem() is ForecastUiState.Loading).isTrue()
                assertThat(awaitItem() is ForecastUiState.Error).isTrue()
            }
        }
}