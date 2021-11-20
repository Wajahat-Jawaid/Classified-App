package com.wajahat.classifiedapptest.di

import android.app.Application
import com.wajahat.classifiedapptest.api.AdsWebService
import com.wajahat.classifiedapptest.data.AppDatabase
import com.wajahat.classifiedapptest.photos.data.AdsRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Module(includes = [ViewModelModule::class, CoreDataModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providePhotoService(
        @PhotoAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, AdsWebService::class.java)

    @Singleton
    @Provides
    fun providePhotoRemoteDataSource(service: AdsWebService) = AdsRemoteDataSource(service)

    @PhotoAPI
    @Provides
    fun providePrivateOkHttpClient(client: OkHttpClient): OkHttpClient {
        // We can add interceptors here
        return client.newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun providePhotoDao(db: AppDatabase) = db.photoDao()

    @CoroutineScopeIO
    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AdsWebService.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory, clazz: Class<T>
    ): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}