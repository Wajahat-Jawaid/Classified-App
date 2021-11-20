package com.wajahat.classifiedapptest.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wajahat.classifiedapptest.util.AUTHOR
import com.wajahat.classifiedapptest.util.DOWNLOAD_URL
import com.wajahat.classifiedapptest.util.ID

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
@Entity(tableName = "ads")
data class Ad(
    @PrimaryKey
    @field:SerializedName(ID)
    val id: String,
    @field:SerializedName(AUTHOR)
    val author: String,
    @field:SerializedName(DOWNLOAD_URL)
    val url: String
)