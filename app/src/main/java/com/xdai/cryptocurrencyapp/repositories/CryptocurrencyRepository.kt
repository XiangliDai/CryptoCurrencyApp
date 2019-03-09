package com.xdai.cryptocurrencyapp.repositories

import android.util.Log
import com.xdai.cryptocurrencyapp.dao.CryptoCurrenciesDao
import com.xdai.cryptocurrencyapp.models.CryptoCurrency
import com.xdai.cryptocurrencyapp.network.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class CryptocurrencyRepository @Inject constructor(val apiInterface: ApiInterface, val cryptoCurrenciesDao: CryptoCurrenciesDao) {

    fun getCryptoCurrencies(): Observable<List<CryptoCurrency>> {
        val getFromDB = getCryptoCurrenciesFromDB()
        val getFromNetwork = getCryptoCurrenciesFromNetwork()
        return Observable.concat(getFromDB, getFromNetwork)
    }

    fun getCryptoCurrenciesFromDB(): Observable<List<CryptoCurrency>> {
        return cryptoCurrenciesDao.queryCryptoCurrencies().toObservable().doOnNext {
            Log.d("CryptocurrencyRepositor", "get data from db: " + it.size.toString())
        }.doOnError {
            Log.e("CryptocurrencyRepositor", "get data from db:" +  it.message)
        }
    }

    fun getCryptoCurrenciesFromNetwork(): Observable<List<CryptoCurrency>> {
        return apiInterface.getCryptoCurrencies("0").doOnNext {
            Log.d("CryptocurrencyRepositor", "get data from network:" + it.size.toString())
            cryptoCurrenciesDao.insertAllCryptoCurrencies(it)
        }.doOnError {
            Log.e("CryptocurrencyRepositor", "get data from network:" + it.message)
        }
    }
}