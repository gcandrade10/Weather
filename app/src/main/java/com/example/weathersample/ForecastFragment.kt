package com.example.weathersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathersample.databinding.FragmentForecastBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!
    private val forecastViewModel: ForecastViewModel by viewModel()
    private val args: ForecastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastViewModel.mainLiveData.observe(viewLifecycleOwner, {
            binding.title.text = it.title
            val adapter = ForecastAdapter(it.list)
            binding.forecasts.layoutManager = LinearLayoutManager(activity)
            binding.forecasts.adapter = adapter
            binding.loadingIndicator.isVisible = false
        })
        forecastViewModel.forecast(args.id)

    }

}