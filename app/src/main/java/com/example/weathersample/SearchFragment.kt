package com.example.weathersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
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
        searchViewModel.mainLiveData.observe(viewLifecycleOwner, { results: List<Result> ->
            binding.searchBox.setOnItemClickListener { _, _, position, _ ->
                Toast.makeText(activity, "${results[position].id}", Toast.LENGTH_SHORT).show()
            }
            val cities = results.map { "${it.title} ${it.type}" }
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, cities)
            binding.searchBox.setAdapter(adapter)
            binding.searchBox.showDropDown()
        })
        binding.searchBox.doAfterTextChanged {
            searchViewModel.search(it.toString())
        }
    }
}