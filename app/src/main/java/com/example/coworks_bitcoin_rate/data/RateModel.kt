package com.example.coworks_bitcoin_rate.data

import com.google.gson.annotations.SerializedName

data class RateModel(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rate")
    val rate: Double,

)
