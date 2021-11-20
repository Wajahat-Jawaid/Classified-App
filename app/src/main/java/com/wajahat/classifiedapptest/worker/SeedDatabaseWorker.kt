package com.wajahat.classifiedapptest.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.wajahat.classifiedapptest.data.AppDatabase
import com.wajahat.classifiedapptest.models.Ad
import com.wajahat.classifiedapptest.util.DATA_FILENAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {
            try {
                applicationContext.assets.open(DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Ad>>() {}.type
                        val list: List<Ad> = Gson().fromJson(jsonReader, type)
                        AppDatabase.getInstance(applicationContext).photoDao().insertAll(list)

                        Result.success()
                    }
                }
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}