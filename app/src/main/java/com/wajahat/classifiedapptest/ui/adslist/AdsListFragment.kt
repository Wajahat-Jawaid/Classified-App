package com.wajahat.classifiedapptest.ui.adslist

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.wajahat.classifiedapptest.MainActivity
import com.wajahat.classifiedapptest.R
import com.wajahat.classifiedapptest.base.BaseFragment
import com.wajahat.classifiedapptest.databinding.FragmentAdsListBinding
import com.wajahat.classifiedapptest.di.Injectable
import com.wajahat.classifiedapptest.di.injectViewModel
import com.wajahat.classifiedapptest.ui.adapters.AdsListAdapter
import com.wajahat.classifiedapptest.ui.extensions.hide
import com.wajahat.classifiedapptest.util.ConnectivityUtil

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AdsListFragment : BaseFragment<FragmentAdsListBinding>(), Injectable {

    private val adapter: AdsListAdapter by lazy { AdsListAdapter() }
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var binding: FragmentAdsListBinding
    private lateinit var viewModel: AdsListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = injectBinding(view)
        viewModel = injectViewModel(viewModelFactory)
        super.onViewCreated(view, savedInstanceState)

        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(requireContext())
        linearLayoutManager = LinearLayoutManager(activity)
        setLayoutManager()
        binding.recyclerView.adapter = adapter

        loadAds(adapter)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as MainActivity).supportActionBar?.setTitle(R.string.app_name)
    }

    private fun loadAds(adapter: AdsListAdapter) {
        viewModel.ads.observe(viewLifecycleOwner) {
            binding.progressBar.hide()
            adapter.submitList(it)
        }
    }

    private fun setLayoutManager() {
        val recyclerView = binding.recyclerView

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()
        }

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.scrollToPosition(scrollPosition)
    }

    override fun getViewId() = R.layout.fragment_ads_list
    override fun injectBinding(view: View) = DataBindingUtil.bind<FragmentAdsListBinding>(view)!!
}