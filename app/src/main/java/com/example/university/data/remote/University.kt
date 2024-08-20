package com.example.university.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class University(
    @SerializedName("alpha_two_code")
    var alphaTwoCode: String? = null,

    @SerializedName("web_pages")
    var webPages: ArrayList<String> = arrayListOf(),

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("domains")
    var domains: ArrayList<String> = arrayListOf(),

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("state-province")
    var stateProvince: String? = null
) : Parcelable