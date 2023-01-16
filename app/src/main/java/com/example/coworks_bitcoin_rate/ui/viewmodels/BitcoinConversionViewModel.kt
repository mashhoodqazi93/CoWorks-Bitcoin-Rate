package com.example.coworks_bitcoin_rate.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.coworks_bitcoin_rate.viewstates.BitcoinConversionViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BitcoinConversionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel(){
    val state = MutableStateFlow(BitcoinConversionViewState())
}