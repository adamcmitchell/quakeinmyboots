package com.adammitchell.earthquakes.feature_earthquakes.presentation.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.extension.*
import com.adammitchell.earthquakes.core.platform.BaseFragment
import com.adammitchell.earthquakes.feature_earthquakes.R
import com.adammitchell.earthquakes.feature_earthquakes.presentation.detail.EarthquakeDetailsActivity
import kotlinx.android.synthetic.main.fragment_earthquakes.*
import javax.inject.Inject
import com.adammitchell.earthquakes.feature_earthquakes.presentation.extensions.getEarthquakeComponent

class EarthquakesFragment : BaseFragment() {

    @Inject
    lateinit var earthquakesAdapter: EarthquakesAdapter

    private lateinit var earthquakesViewModel: EarthquakesViewModel

    override fun layoutId() = R.layout.fragment_earthquakes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = this.getEarthquakeComponent()
        component.inject(this);

        earthquakesViewModel = viewModel(viewModelFactory) {
            observe(earthquakes, ::renderEarthquakeList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadEarthquakeList()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        newConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
    }

    private fun initializeView() {
        earthquakeList.layoutManager = StaggeredGridLayoutManager(resources.getInteger(R.integer.earthquake_list_span_count), StaggeredGridLayoutManager.VERTICAL)
        earthquakeList.adapter = earthquakesAdapter
        earthquakesAdapter.clickListener = { earthquake ->
            activity?.let {
                showEarthquakeDetails(it, earthquake)
            }
        }
    }

    private fun loadEarthquakeList() {
        emptyView.setGone()
        earthquakeList.setVisible()
        showProgress()
        earthquakesViewModel.loadEarthquakes()
    }

    private fun renderEarthquakeList(earthquakes: List<EarthquakeView>?) {
        earthquakesAdapter.collection = earthquakes.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        earthquakeList.setGone()
        emptyView.setVisible()
        hideProgress()
        notifyWithAction(message, R.string.action_refresh, ::loadEarthquakeList)
    }

    private fun showEarthquakeDetails(activity: FragmentActivity, earthquakeView: EarthquakeView) {
        val intent = EarthquakeDetailsActivity.callingIntent(activity, earthquakeView)
        activity.startActivity(intent)
    }
}