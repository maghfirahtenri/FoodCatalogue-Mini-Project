package com.kocaksapp.foodcatalogue.ui.home.category.vegetarian

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kocaksapp.foodcatalogue.data.response.MealsItem
import com.kocaksapp.foodcatalogue.databinding.FragmentCategoryBinding
import com.kocaksapp.foodcatalogue.ui.home.HomeAdapter

class VegetarianCategoryFragment: Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding as FragmentCategoryBinding
    private lateinit var adapter: HomeAdapter

    private val vegetarianMainViewModel: VegetarianMainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter()
        observingValue()
        vegetarianMainViewModel.getMeals()
        showRecyclerView()
    }

    private fun observingValue() {
        vegetarianMainViewModel.listMeals.observe(viewLifecycleOwner) {
            adapter.mealsList = it as ArrayList<MealsItem>
        }

        vegetarianMainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.pbReportType.visibility = View.VISIBLE
            } else {
                binding.pbReportType.visibility = View.GONE
            }
        }

        vegetarianMainViewModel.message.observe(viewLifecycleOwner) {
            binding.tvMessageReportType.text = it
        }
    }



    private fun showRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(activity)

        binding.rvReportType.layoutManager = linearLayoutManager
        binding.rvReportType.setHasFixedSize(true)
        binding.rvReportType.adapter = adapter
    }
}