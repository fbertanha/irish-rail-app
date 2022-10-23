package com.felipebertanha.irishrailapp.featureforecast.domain.usecase

import com.felipebertanha.irishrailapp.commom.Resource
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import com.felipebertanha.irishrailapp.featureforecast.domain.repository.ForecastRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.Calendar

/**
 * Created by felipebertanha on 23/October/2022
 */
class GetTrainArrivalForecastBasedOnTimeUseCaseTest {

    private lateinit var repository: ForecastRepository
    private lateinit var getTrainArrivalForecastBasedOnTimeUseCase: GetTrainArrivalForecastBasedOnTimeUseCase

    @Before
    fun setup() {
        repository = mockk<ForecastRepository>()
        getTrainArrivalForecastBasedOnTimeUseCase =
            GetTrainArrivalForecastBasedOnTimeUseCase(repository)
    }

    @Test
    fun `Given Its 00-00 hours When I request trains forecast Then I should see forecast from Pearse towards Northbound`() =
        runBlocking {
            every {
                repository.getTrainsByStationCode("perse")
            } returns flow {
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

            //GIVEN
            val givenTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
            }.time

            //WHEN
            val first = getTrainArrivalForecastBasedOnTimeUseCase(givenTime).first()

            //THEN
            verify { repository.getTrainsByStationCode("perse") }
            val trains = (first as Resource.Success).data
            trains.forEach { train ->
                assertThat(train.direction).isEqualTo("Northbound")
            }
        }

    @Test
    fun `Given Its 12-00 hours When I request trains forecast Then I should see forecast from Pearse towards Northbound`() =
        runBlocking {
            every {
                repository.getTrainsByStationCode("perse")
            } returns flow {
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

            //GIVEN
            val givenTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 12)
                set(Calendar.MINUTE, 0)
            }.time

            //WHEN
            val first = getTrainArrivalForecastBasedOnTimeUseCase(givenTime).first()

            //THEN
            verify { repository.getTrainsByStationCode("perse") }
            val trains = (first as Resource.Success).data
            trains.forEach { train ->
                assertThat(train.direction).isEqualTo("Northbound")
            }
        }

    @Test
    fun `Given Its 12-01 hours When I request trains forecast Then I should see forecast from Howth towards Southbound`() =
        runBlocking {
            every {
                repository.getTrainsByStationCode("howth")
            } returns flow {
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

            //GIVEN
            val givenTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 12)
                set(Calendar.MINUTE, 1)
            }.time

            //WHEN
            val first = getTrainArrivalForecastBasedOnTimeUseCase(givenTime).first()

            //THEN
            verify { repository.getTrainsByStationCode("howth") }
            val trains = (first as Resource.Success).data
            trains.forEach { train ->
                assertThat(train.direction).isEqualTo("Southbound")
            }
        }

    @Test
    fun `Given Its 23-59 hours When I request trains forecast Then I should see forecast from Howth towards Southbound`() =
        runBlocking {
            every {
                repository.getTrainsByStationCode("howth")
            } returns flow {
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

            //GIVEN
            val givenTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
            }.time

            //WHEN
            val first = getTrainArrivalForecastBasedOnTimeUseCase(givenTime).first()

            //THEN
            verify { repository.getTrainsByStationCode("howth") }
            val trains = (first as Resource.Success).data
            trains.forEach { train ->
                assertThat(train.direction).isEqualTo("Southbound")
            }
        }
}