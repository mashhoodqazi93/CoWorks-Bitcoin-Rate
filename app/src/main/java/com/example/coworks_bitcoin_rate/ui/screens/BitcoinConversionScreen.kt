package com.example.coworks_bitcoin_rate.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coworks_bitcoin_rate.ui.viewmodels.BitcoinConversionViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coworks_bitcoin_rate.R
import com.example.coworks_bitcoin_rate.ui.viewmodels.BitcoinConversionViewModel.BitcoinConversioonEvent.RefreshClicked

@Composable
fun BitcoinConversionScreen() {
    val viewModel: BitcoinConversionViewModel = hiltViewModel()
    RestaurantListUi(viewModel)
}

@Composable
fun RestaurantListUi(viewModel: BitcoinConversionViewModel) {
    val stateFlow = viewModel.state.collectAsStateWithLifecycle()
    val state = stateFlow.value
    val usdValue = state.usdValue
    val handleEvent = viewModel::handleEvents
    val isLoading = state.isLoading

    Column(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.current_bitocoin_rate),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = if (usdValue != "-") stringResource(
                R.string.currency_value,
                usdValue
            ) else "-",
            style = MaterialTheme.typography.h5
        )
        AnimatedVisibility(isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(24.dp)
            )
        }
        AnimatedVisibility(isLoading.not()) {
            Button(
                onClick = { handleEvent(RefreshClicked) }, modifier = Modifier.padding(top = 16.dp),
            ) {
                Text(text = stringResource(R.string.refresh))
            }
        }
    }
}