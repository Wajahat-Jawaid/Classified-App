package com.wajahat.classifiedapptest.di

import com.wajahat.classifiedapptest.ui.addetails.AdDetailsFragment
import com.wajahat.classifiedapptest.ui.adslist.AdsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePhotosListFragment(): AdsListFragment

    @ContributesAndroidInjector
    abstract fun contributePhotoDetailsFragment(): AdDetailsFragment
}
