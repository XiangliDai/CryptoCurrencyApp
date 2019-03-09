package com.xdai.cryptocurrencyapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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