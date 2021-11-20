package com.wajahat.classifiedapptest.photos.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.wajahat.classifiedapptest.models.Ad
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AdsPageDataSourceFactory @Inject constructor(
    private val dataSource: AdsRemoteDataSource,
    private val dao: AdDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Ad>() {

    private val liveData = MutableLiveData<AdsPageDataSource>()

    override fun create(): DataSource<Int, Ad> {
        val source = AdsPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}