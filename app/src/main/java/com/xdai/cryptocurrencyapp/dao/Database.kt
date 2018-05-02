package com.xdai.cryptocurrencyapp.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.xdai.cryptocurrencyapp.models.CryptoCurrency

@Database(entities = arrayOf(CryptoCurrency::class), version = 1)

abstract class Database: RoomDatabase(){
    abstract fun cryptoCurrenciesDao(): CryptoCurrenciesDao
}