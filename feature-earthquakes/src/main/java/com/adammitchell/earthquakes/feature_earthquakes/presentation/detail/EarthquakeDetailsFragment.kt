package com.adammitchell.earthquakes.feature_earthquakes.presentation.detail

import android.os.Bundle
import android.view.View
import com.adammitchell.earthquakes.core.platform.BaseFragment
import com.adammitchell.earthquakes.core.exception.Failure
import com.adammitchell.earthquakes.core.exception.Failure.NetworkConnection
import com.adammitchell.earthquakes.core.exception.Failure.ServerError
import com.adammitchell.earthquakes.core.extension.*
import com.adammitchell.earthquakes.feature_earthquakes.presentation.list.EarthquakeView
import com.adammitchell.earthquakes.feature_earthquakes.R
import com.adammitchell.earthquakes.feature_earthquakes.presentation.extensions.getEarthquakeComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_earthquake_details.*
import kotlinx.android.synthetic.main.fragment_earthquake_details_bottom_sheet.*

class EarthquakeDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_EARTHQUAKE = "param_earthquake"

        fun forEarthquake(earthquake: EarthquakeView?): EarthquakeDetailsFragment {
            val fragment =
                EarthquakeDetailsFragment()
            earthquake?.let {
                val arguments = Bundle()
                arguments.putParcelable(PARAM_EARTHQUAKE, it)
                fragment.arguments = arguments
            }
            return fragment
        }
    }

    private lateinit var earthquakeDetailsViewModel:  EarthquakeDetailsViewModel
    private lateinit var mapFragment: SupportMapFragment

    override fun layoutId() = R.layout.fragment_earthquake_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = this.getEarthquakeComponent()
        component.inject(this)

        earthquakeDetailsViewModel = viewModel(viewModelFactory) {
            observe(earthquakeDetails, ::renderEarthquakeDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            earthquakeDetailsViewModel.loadEarthquakeDetails((arguments?.get(PARAM_EARTHQUAKE) as EarthquakeView))
        }
        mapFragment = SupportMapFragment();
        childFragmentManager.beginTransaction().replace(R.id.earthquakeMap, mapFragment).commit()
    }

    private fun renderEarthquakeDetails(earthquake: EarthquakeDetailsView?) {
        earthquake?.let {
            // TODO This could be a recycler
            earthquakeLocation.text = "${earthquake.latitude}, ${earthquake.longitude}"
            earthquakeMagnitude.text = "${earthquake.magnitude}"
            earthquakeDepth.text = "${earthquake.depth}"

            mapFragment.getMapAsync { mMap ->
                val epicenter = LatLng(earthquake.latitude, earthquake.longitude)
                mMap.addMarker(
                    MarkerOptions()
                        .position(epicenter)
                )
                mMap.moveCamera(CameraUpdateFactory.newLatLng(epicenter))
            }
        }

        scrollView?.let { scroll ->
            BottomSheetBehavior.from(scroll).let {
                earthquake_details_tap_action_layout.setOnClickListener { _ ->
                    when(it.state) {
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            it.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            it.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                }
            }
        }

    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is ServerError -> { notify(R.string.failure_server_error); close() }
        }
    }
}
