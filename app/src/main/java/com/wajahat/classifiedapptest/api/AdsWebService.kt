package com.wajahat.classifiedapptest.api

import com.wajahat.classifiedapptest.models.Ad
import com.wajahat.classifiedapptest.util.ID
import com.wajahat.classifiedapptest.util.LIMIT
import com.wajahat.classifiedapptest.util.PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
interface AdsWebService {

    companion object {
        const val BASE_URL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/"
    }

    @GET("${BASE_URL}dynamodb-writer")
    suspend fun getPhotos(
        @Query(PAGE) page: Int,
        @Query(LIMIT) limit: Int
    ): Response<List<Ad>>

    @GET("${BASE_URL}id/{id}/info")
    suspend fun getPhoto(@Path(ID) id: String): Response<Ad>
}