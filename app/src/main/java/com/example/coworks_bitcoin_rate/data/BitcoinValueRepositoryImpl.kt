package com.example.coworks_bitcoin_rate.data

import com.example.coworks_bitcoin_rate.domain.BitcoinValueRepository
import retrofit2.Response
import javax.inject.Inject

class BitcoinValueRepositoryImpl @Inject constructor(
    private val remoteDataSource: BitcoinValuesRemoteDataSource
): BitcoinValueRepository {
    override suspend fun getBitcoinValue(): Response<List<RateModel>> {
        return remoteDataSource.getBitcoinValue()
    }
}