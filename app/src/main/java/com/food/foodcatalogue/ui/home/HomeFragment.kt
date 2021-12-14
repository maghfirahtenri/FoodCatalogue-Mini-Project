package com.kocaksapp.foodcatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kocaksapp.foodcatalogue.R
import com.kocaksapp.foodcatalogue.data.response.RandomItem
import com.kocaksapp.foodcatalogue.databinding.FragmentHomeBinding
import com.kocaksapp.foodcatalogue.ui.detail.DetailActivity
import com.kocaksapp.foodcatalogue.ui.home.category.vegetarian.VegetarianCategoryFragment

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private val homeViewModel : HomeViewModel by viewModels()
    private var dataRandom : RandomItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllCategoryDefaultFragment()
        observingValue()

        binding.cardImg.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
                .putExtra(DetailActivity.PASSING_DATA_RANDOM_ITEM, dataRandom)
            startActivity(intent)
        }

        homeViewModel.getMeals()
    }

    private fun observingValue() {
        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.pbHome.visibility = View.VISIBLE
            } else {
                binding.pbHome.visibility = View.GONE
            }
        }

        homeViewModel.listMeals.observe(viewLifecycleOwner) {
            for (item in it) {
                dataRandom = item

                Glide.with(requireContext())
                    .load(item.strMealThumb)
                    .into(binding.ivEvent)

                binding.tvTitle.text = item.strMeal
                binding.tvDateTime.text = item.strArea

                Log.d("Ikang:::", item.strMeal!!)
            }
        }
    }

    private fun setAllCategoryDefaultFragment() {
        val fragmentManager = childFragmentManager
        val allCategoryFragment = VegetarianCategoryFragment()

        fragmentManager.beginTransaction()
            .replace(R.id.type_report_container, allCategoryFragment, VegetarianCategoryFragment::class.java.simpleName)
            .commit()
    }
}