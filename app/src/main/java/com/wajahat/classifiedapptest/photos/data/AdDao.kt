package com.wajahat.classifiedapptest.photos.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wajahat.classifiedapptest.models.Ad

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Dao
interface AdDao {

    @Query("SELECT * FROM ads ORDER BY author DESC")
    fun getPagedPhotos(): DataSource.Factory<Int, Ad>

    @Query("SELECT * FROM ads WHERE id = :id")
    fun getPhoto(id: String): LiveData<Ad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(ads: List<Ad>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ad: Ad)
}
