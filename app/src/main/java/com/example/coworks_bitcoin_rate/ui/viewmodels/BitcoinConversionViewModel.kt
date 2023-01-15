package com.example.coworks_bitcoin_rate.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coworks_bitcoin_rate.domain.GetUsdValueForBitcoin
import com.example.coworks_bitcoin_rate.ui.ComposeUiEvent
import com.example.coworks_bitcoin_rate.ui.viewstates.BitcoinConversionViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitcoinConversionViewModel @Inject constructor(
    private val getUsdValueForBitcoin: GetUsdValueForBitcoin
) : ViewModel() {
    val state = MutableStateFlow(BitcoinConversionViewState())

    init {
        getBitcoinUsdValue()
    }

    fun handleEvents(event: ComposeUiEvent) {
        when(event) {
            is BitcoinConversioonEvent.RefreshClicked -> getBitcoinUsdValue()
        }
    }

    private fun getBitcoinUsdValue() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            val usdValue = getUsdValueForBitcoin()
            state.value = state.value.copy(isLoading = false, usdValue = usdValue ?: "-")
        }
    }

    sealed class BitcoinConversioonEvent: ComposeUiEvent() {
        object RefreshClicked: BitcoinConversioonEvent()
    }
}