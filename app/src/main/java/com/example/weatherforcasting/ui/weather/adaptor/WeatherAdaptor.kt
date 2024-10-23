package com.example.weatherforcasting.ui.weather.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforcasting.R
import com.example.weatherforcasting.data.model.WeatherItem
import com.example.weatherforcasting.databinding.OneItemWeatherBinding
import com.example.weatherforcasting.util.getDate

class WeatherAdaptor(
    // one selection
    var context: Context, var arrayList: ArrayList<WeatherItem>,
)
// var selectedArrayList: ArrayList<ModelTrip>
    :
    RecyclerView.Adapter<WeatherAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OneItemWeatherBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(
            binding
        )

    }

    override fun getItemCount(): Int {

        return arrayList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(arrayList[position])

    }

    inner class ViewHolder(val itemViews: OneItemWeatherBinding) :
        RecyclerView.ViewHolder(itemViews.root) {
        fun bindItems(
            currentItem: WeatherItem?
        ) {
            itemViews.weatherDesc.text = context.getString(
                R.string.weather_desc,
                currentItem?.weather?.get(0)?.description ?: ""
            )
            itemViews.temp.text =
                context.getString(R.string.temp, currentItem?.main?.temp?.toString())
            itemViews.dateString.text =
                getDate(currentItem?.dtTxt?:"")
            itemViews.pressure.text =
                context.getString(R.string.pressure, currentItem?.main?.pressure?.toString())

        }


    }

    fun updateList(results: List<WeatherItem>) {
        arrayList.addAll(results)
        notifyDataSetChanged()
    }

}