package com.example.coworks_bitcoin_rate.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coworks_bitcoin_rate.BitcoinNavigation
import com.example.coworks_bitcoin_rate.R
import com.example.coworks_bitcoin_rate.domain.GetUsdValueForBitcoin
import com.example.coworks_bitcoin_rate.ui.ComposeUiEvent
import com.example.coworks_bitcoin_rate.ui.viewstates.BitcoinConversionViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BitcoinConversionViewModel @Inject constructor(
    private val getUsdValueForBitcoin: GetUsdValueForBitcoin
) : ViewModel() {
    val state = MutableStateFlow(BitcoinConversionViewState())
    val navigation: MutableSharedFlow<BitcoinNavigation> = MutableSharedFlow()
    init {
        getBitcoinUsdValue()
    }

    fun handleEvents(event: ComposeUiEvent) {
        when(event) {
            is BitcoinConversionEvent.RefreshClicked -> getBitcoinUsdValue()
            is BitcoinConversionEvent.RefreshError -> getBitcoinUsdValue()
        }
    }

    private fun getBitcoinUsdValue() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            val usdValue = getUsdValueForBitcoin()
            state.value = state.value.copy(isLoading = false, usdValue = usdValue ?: "-")
            if(usdValue.isNullOrEmpty()) {
                navigateTo { BitcoinNavigation.Snackbar(R.string.data_could_not_be_loaded) }
            }
        }
    }

    private fun navigateTo(navigate: () -> BitcoinNavigation) {
        viewModelScope.launch {
            navigation.emit(navigate())
        }
    }

    sealed class BitcoinConversionEvent: ComposeUiEvent() {
        object RefreshClicked: BitcoinConversionEvent()
        object RefreshError: BitcoinConversionEvent()
    }
}