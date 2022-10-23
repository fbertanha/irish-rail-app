package com.felipebertanha.irishrailapp.featureforecast.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.felipebertanha.irishrailapp.R
import com.felipebertanha.irishrailapp.databinding.ActivityForecastBinding
import com.felipebertanha.irishrailapp.extensions.ViewExtensions.hide
import com.felipebertanha.irishrailapp.extensions.ViewExtensions.show
import com.felipebertanha.irishrailapp.extensions.ViewExtensions.showOrHide
import com.felipebertanha.irishrailapp.featureforecast.domain.model.Train
import com.felipebertanha.irishrailapp.featureforecast.presentation.listadapter.TrainsAdapter
import com.felipebertanha.irishrailapp.featureforecast.presentation.uistate.ForecastUiState
import com.felipebertanha.irishrailapp.featureforecast.presentation.viewmodel.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    private val viewModel: ForecastViewModel by viewModels()
    private lateinit var binding: ActivityForecastBinding
    private val trainsAdapter: TrainsAdapter by lazy { TrainsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        subscribeUi()
        viewModel.getTrains()
    }

    private fun setupView() {
        binding.rvTrains.adapter = trainsAdapter
        binding.rvTrains.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_forecast_refresh -> {
                viewModel.getTrains()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_forecast, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is ForecastUiState.Loading -> showLoading()
                        is ForecastUiState.TrainsLoaded -> showTrains(uiState.trains)
                        is ForecastUiState.Error -> showError(uiState.throwable)
                    }
                }
            }
        }
    }

    private fun showLoading(shouldShow: Boolean = true) {
        binding.pgLoading.showOrHide(shouldShow)
        binding.rvTrains.showOrHide(shouldShow.not())
        binding.tvMessage.showOrHide(shouldShow.not())
    }

    private fun showTrains(trains: List<Train>) {
        showLoading(false)
        trainsAdapter.submitList(trains)
        binding.rvTrains.show()
        binding.tvMessage.hide()
    }

    private fun showError(throwable: Throwable) {
        showLoading(false)
        showMessage(throwable.localizedMessage)
    }

    private fun showMessage(text: String?) {
        showLoading(false)
        binding.tvMessage.show()
        binding.rvTrains.hide()
        binding.tvMessage.text = text
    }


}