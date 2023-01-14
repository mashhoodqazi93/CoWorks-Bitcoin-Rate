package com.example.coworks_bitcoin_rate

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coworks_bitcoin_rate.BitcoinNavigation.BitcoinConversion
import com.example.coworks_bitcoin_rate.ui.screens.BitcoinConversionScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = BitcoinConversion.route) {
        addBitcoinConversionScreen()
    }
}

private fun NavGraphBuilder.addBitcoinConversionScreen(){
    composable(BitcoinConversion.route) {
        BitcoinConversionScreen()
    }
}

sealed class BitcoinNavigation(val route: String) {
    object BitcoinConversion: BitcoinNavigation("bitcoin_conversion")
}