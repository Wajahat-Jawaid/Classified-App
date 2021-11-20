package com.wajahat.classifiedapptest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.wajahat.classifiedapptest.base.BaseFragment
import com.wajahat.classifiedapptest.databinding.FragmentSplashBinding

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        injectBinding(view)
        super.onViewCreated(view, savedInstanceState)
        // For fake loading
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splash_fragment_to_adsList_fragment)
        }, FAKE_LOADING_DURATION)
    }

    companion object {
        private const val FAKE_LOADING_DURATION = 2L
    }

    override fun getViewId() = R.layout.fragment_splash
    override fun injectBinding(view: View) = DataBindingUtil.bind<FragmentSplashBinding>(view)!!
}