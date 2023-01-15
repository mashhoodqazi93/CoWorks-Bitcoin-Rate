package com.example.coworks_bitcoin_rate

import com.example.coworks_bitcoin_rate.data.BitcoinRateService
import com.example.coworks_bitcoin_rate.data.BitcoinValueRepositoryImpl
import com.example.coworks_bitcoin_rate.domain.BitcoinValueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BitcoinModule {
    @Singleton
    @Provides
    fun provideRepository(repository: BitcoinValueRepositoryImpl): BitcoinValueRepository = repository

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://bitpay.com/").addConverterFactory(GsonConverterFactory.create()).build()
    @Provides
    fun provideService(retrofit: Retrofit): BitcoinRateService = retrofit.create(BitcoinRateService::class.java)
}