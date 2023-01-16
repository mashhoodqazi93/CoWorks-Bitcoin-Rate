package com.example.coworks_bitcoin_rate.domain

import kotlinx.coroutines.delay
import java.text.NumberFormat
import javax.inject.Inject

class GetUsdValueForBitcoin @Inject constructor(
    private val bitcoinValueRepository: BitcoinValueRepository
) {
    suspend operator fun invoke(): String? {
        val result = bitcoinValueRepository.getBitcoinValue()
        var usdRate: Double? = null
        result?.let {

        }
        result?.let {
            if (result.isSuccessful) {
                usdRate = result.body()?.filter {
                    it.code == Currency.USD.name
                }?.firstOrNull()?.rate
            }
        }
        delay(3000)
        return if (usdRate != null) NumberFormat.getInstance().format(usdRate) else usdRate
    }
}