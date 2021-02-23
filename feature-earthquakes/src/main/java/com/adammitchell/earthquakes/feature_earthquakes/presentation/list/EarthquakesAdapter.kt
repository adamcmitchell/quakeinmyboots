package com.adammitchell.earthquakes.feature_earthquakes.presentation.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adammitchell.earthquakes.core.extension.inflate
import com.adammitchell.earthquakes.feature_earthquakes.R
import kotlinx.android.synthetic.main.row_earthquake.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

class EarthquakesAdapter
@Inject constructor() : RecyclerView.Adapter<EarthquakesAdapter.ViewHolder>() {

    internal var collection: List<EarthquakeView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    internal var clickListener: (EarthquakeView) -> Unit = { _ -> }

    internal var dateFormatter = SimpleDateFormat("E, dd MMM yyyy HH:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            parent.inflate(R.layout.row_earthquake)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position], dateFormatter, clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(earthquakeView: EarthquakeView, dateFormatter: DateFormat, clickListener: (EarthquakeView) -> Unit) {
            itemView.earthquakeLocationFlag.text = earthquakeView.countryCode ?. let {
                countryCodeToEmojiFlag(it)
            } ?: "\uD83C\uDF0D"
            itemView.earthquakeLocation.text = earthquakeView.locationName
            itemView.earthquakeDate.text = dateFormatter.format(earthquakeView.date);
            itemView.earthquakeMagnitude.text = "${earthquakeView.magnitude}"
            itemView.setOnClickListener { clickListener(earthquakeView) }
            val resourceId =  if (earthquakeView.magnitude >= 8.0) { R.drawable.background_mag_high } else { R.drawable.background_mag_low }
            itemView.earthquakeMagnitude.setBackgroundResource(resourceId)
        }

        private fun countryCodeToEmojiFlag(countryCode: String): String {
            return countryCode
                .toUpperCase(Locale.US)
                .map { char ->
                    Character.codePointAt("$char", 0) - 0x41 + 0x1F1E6
                }
                .map { codePoint ->
                    Character.toChars(codePoint)
                }
                .joinToString(separator = "") { charArray ->
                    String(charArray)
                }
        }
    }
}