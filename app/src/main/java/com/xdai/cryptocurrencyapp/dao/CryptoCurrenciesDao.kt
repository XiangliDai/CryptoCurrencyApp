package com.xdai.cryptocurrencyapp.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.xdai.cryptocurrencyapp.models.CryptoCurrency
import io.reactivex.Single
@Dao

interface CryptoCurrenciesDao {

    @Query("SELECT * FROM cryptocurrencies")
fun queryCryptoCurrencies(): Single<List<CryptoCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoCurrency(cryptoCurrency: CryptoCurrency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCryptoCurrencies(cryptoCurrencies: List<CryptoCurrency>)

}