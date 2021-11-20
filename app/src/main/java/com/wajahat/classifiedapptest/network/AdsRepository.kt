package com.wajahat.classifiedapptest.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.wajahat.classifiedapptest.data.resultLiveData
import com.wajahat.classifiedapptest.models.Ad
import com.wajahat.classifiedapptest.photos.data.AdDao
import com.wajahat.classifiedapptest.photos.data.AdsPageDataSourceFactory
import com.wajahat.classifiedapptest.photos.data.AdsRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Singleton
//@OpenForTesting
class AdsRepository @Inject constructor(
    private val dao: AdDao,
    private val remoteDataSource: AdsRemoteDataSource
) {

    /* If there is the internet, get the data from the remote API. Otherwise get the ones
    * stored in Room DB if there are
    */
    fun observePagedAds(connectivityAvailable: Boolean, coroutineScope: CoroutineScope) =
        if (connectivityAvailable) observeRemotePagedAds(coroutineScope)
        else observeLocalPagedAds()

    /* Get local photos */
    private fun observeLocalPagedAds(): LiveData<PagedList<Ad>> {
        return LivePagedListBuilder(
            dao.getPagedPhotos(),
            AdsPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    /* Get photos from remote API */
    private fun observeRemotePagedAds(ioCoroutineScope: CoroutineScope)
            : LiveData<PagedList<Ad>> {
        val dataSourceFactory = AdsPageDataSourceFactory(
            remoteDataSource,
            dao, ioCoroutineScope
        )
        return LivePagedListBuilder(
            dataSourceFactory,
            AdsPageDataSourceFactory.pagedListConfig()
        ).build()
    }

    /* Get the one from the local DB (i.e. Room). Meanwhile, trigger the network call to fetch
     * the updated data and then update the local DB with the updated data fetched from
     * remote API */
    fun observePhoto(id: String) = resultLiveData(
        databaseQuery = { dao.getPhoto(id) },
        networkCall = { remoteDataSource.getPhoto(id) },
        saveCallResult = { dao.insert(it) })
        .distinctUntilChanged()
}