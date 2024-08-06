package com.example.university.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.university.util.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "universities")
data class University(
    @SerializedName("alpha_two_code")
    var alphaTwoCode: String? = null,

    @SerializedName("web_pages")
    @TypeConverters(Converters::class)
    var webPages: ArrayList<String> = arrayListOf(),

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("domains")
    @TypeConverters(Converters::class)
    var domains: ArrayList<String> = arrayListOf(),

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("state-province")
    var stateProvince: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
