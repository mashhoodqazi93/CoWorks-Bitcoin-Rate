package com.example.coworks_bitcoin_rate.domain

import com.example.coworks_bitcoin_rate.data.RateModel
import retrofit2.Response

interface BitcoinValueRepository {
    suspend fun getBitcoinValue(): Response<List<RateModel>>
}