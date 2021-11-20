package com.wajahat.classifiedapptest.photos.data

import com.wajahat.classifiedapptest.api.BaseDataSource
import com.wajahat.classifiedapptest.api.AdsWebService
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AdsRemoteDataSource @Inject constructor(private val service: AdsWebService) :
    BaseDataSource() {

    suspend fun getPhotos(page: Int, limit: Int) = getResult { service.getPhotos(page, limit) }
    suspend fun getPhoto(id: String) = getResult { service.getPhoto(id) }
}
