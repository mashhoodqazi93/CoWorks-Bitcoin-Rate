package com.example.coworks_bitcoin_rate.ui.viewstates

import com.example.coworks_bitcoin_rate.R

data class BitcoinConversionViewState(
    val isLoading: Boolean = false,
    val usdValue:String = "-",
    val erorrMessage: Int = R.string.data_could_not_be_loaded
)
