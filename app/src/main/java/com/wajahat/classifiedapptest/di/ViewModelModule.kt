package com.wajahat.classifiedapptest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wajahat.classifiedapptest.ui.addetails.AdDetailsViewModel
import com.wajahat.classifiedapptest.ui.adslist.AdsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AdsListViewModel::class)
    abstract fun bindAdsViewModel(viewModel: AdsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdDetailsViewModel::class)
    abstract fun bindAdDetailsViewModel(viewModel: AdDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
