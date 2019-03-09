package com.xdai.cryptocurrencyapp.dao


import androidx.room.Database
import androidx.room.RoomDatabase
import com.xdai.cryptocurrencyapp.models.CryptoCurrency

@Database(entities = [CryptoCurrency::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun cryptoCurrenciesDao(): CryptoCurrenciesDao
}