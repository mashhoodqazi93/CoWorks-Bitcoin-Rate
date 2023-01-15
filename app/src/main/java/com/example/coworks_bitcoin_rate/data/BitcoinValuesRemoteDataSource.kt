package com.example.coworks_bitcoin_rate.data

import retrofit2.Response
import javax.inject.Inject

class BitcoinValuesRemoteDataSource @Inject constructor(
    private val bitcoinRateService: BitcoinRateService
) {
    suspend fun getBitcoinValue(): Response<List<RateModel>>{
        return bitcoinRateService.getBitCoinRates()
    }
}