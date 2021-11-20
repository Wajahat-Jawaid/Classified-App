package com.wajahat.classifiedapptest.ui.addetails

import androidx.lifecycle.ViewModel
import com.wajahat.classifiedapptest.network.AdsRepository
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AdDetailsViewModel @Inject constructor(repository: AdsRepository) : ViewModel() {

    lateinit var id: String

    val ad by lazy { repository.observePhoto(id) }
}