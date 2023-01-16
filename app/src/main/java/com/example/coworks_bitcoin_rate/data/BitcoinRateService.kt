package com.example.coworks_bitcoin_rate.data

import retrofit2.Response
import retrofit2.http.GET

interface BitcoinRateService {
    @GET("api/rates")
    suspend fun getBitCoinRates(): Response<List<RateModel>>
}