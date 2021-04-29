package com.example.weathersample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathersample.databinding.ForecastItemBinding

class ForecastAdapter(private val forecasts: List<Forecast>) :
    RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemBinding =
            ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecasts[position])
    }

    override fun getItemCount() = forecasts.size

}

class ForecastViewHolder(private val itemBinding: ForecastItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(forecast: Forecast) {
        itemBinding.date.text = forecast.date
        itemBinding.icon.setImageResource(forecast.toIcon())
        itemBinding.title.text = forecast.name
        itemBinding.temperature.text = "${forecast.temperature}"
    }
}

private fun Forecast.toIcon() = when (icon) {
    "c" -> R.drawable.ic_c
    "h" -> R.drawable.ic_h
    "hc" -> R.drawable.ic_hc
    "hr" -> R.drawable.ic_hr
    "lc" -> R.drawable.ic_lc
    "lr" -> R.drawable.ic_lr
    "s" -> R.drawable.ic_s
    "sl" -> R.drawable.ic_sl
    "sn" -> R.drawable.ic_sn
    else -> R.drawable.ic_t
}
