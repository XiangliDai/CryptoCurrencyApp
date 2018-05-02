package com.xdai.cryptocurrencyapp.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class CryptocurrenciesViewModelFactory @Inject constructor(private val cryptocurrenciesViewModel: CryptocurrenciesViewModel)    :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CryptocurrenciesViewModel::class.java!!)){
            return cryptocurrenciesViewModel as T
        }
        throw IllegalArgumentException("Unknow class")
    }
}