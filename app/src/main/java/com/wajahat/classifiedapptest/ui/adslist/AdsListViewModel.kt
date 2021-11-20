package com.wajahat.classifiedapptest.ui.adslist

import androidx.lifecycle.ViewModel
import com.wajahat.classifiedapptest.di.CoroutineScopeIO
import com.wajahat.classifiedapptest.network.AdsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AdsListViewModel @Inject constructor(
    private val repository: AdsRepository,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {

    var connectivityAvailable: Boolean = false

    val ads by lazy {
        repository.observePagedAds(
            connectivityAvailable, ioCoroutineScope
        )
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }
}