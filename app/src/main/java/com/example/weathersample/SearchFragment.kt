package com.example.weathersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.weathersample.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLiveDataObserver(view)
        setUpErrorObserver(view)
        binding.searchBox.doAfterTextChanged {
            searchViewModel.search(it.toString())
            binding.loadingIndicator.isVisible = true
        }
    }

    private fun setUpErrorObserver(view: View) =
        searchViewModel.errorLiveData.observe(viewLifecycleOwner, {
            binding.loadingIndicator.isVisible = false
            Toast.makeText(view.context, R.string.general_error, Toast.LENGTH_SHORT).show()
        })

    private fun setUpLiveDataObserver(view: View) =
        searchViewModel.mainLiveData.observe(viewLifecycleOwner, { results: List<Result> ->
            binding.loadingIndicator.isVisible = false
            binding.searchBox.setOnItemClickListener { _, _, position, _ ->
                val id = results[position].id
                val action = SearchFragmentDirections.actionSearchFragmentToForecastFragment(id)
                binding.searchBox.text.clear()
                findNavController(this).navigate(action)
            }
            val cities = results.map { "${it.title} ${it.type}" }
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, cities)
            binding.searchBox.setAdapter(adapter)
            binding.searchBox.showDropDown()
        })

}
