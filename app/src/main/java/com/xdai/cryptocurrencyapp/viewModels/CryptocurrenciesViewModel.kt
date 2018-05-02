package com.xdai.cryptocurrencyapp.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.xdai.cryptocurrencyapp.models.CryptoCurrency
import com.xdai.cryptocurrencyapp.repositories.CryptocurrencyRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CryptocurrenciesViewModel @Inject constructor(private val cryptocurrencyRepository: CryptocurrencyRepository) :ViewModel() {

    val cryptoCurrenciesResult: MutableLiveData<List<CryptoCurrency>> = MutableLiveData()
    val cryptoCurrenciesError: MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<CryptoCurrency>>
    var isLoading = ObservableField(true)
    fun cryptoCurrenciesResult(): LiveData<List<CryptoCurrency>>{
        return cryptoCurrenciesResult
    }

    fun cryptoCurrenciesError(): LiveData<String>{
        return cryptoCurrenciesError
}

    fun loadCryptoCurrencies(){
        isLoading.set(true)
        disposableObserver = object : DisposableObserver<List<CryptoCurrency>>(){
            override fun onComplete() {
            }

            override fun onNext(t: List<CryptoCurrency>) {
                isLoading.set(false)
                cryptoCurrenciesResult.postValue(t)
            }

            override fun onError(e: Throwable) {
                isLoading.set(false)
                cryptoCurrenciesError.postValue(e.message)
            }

        }
        cryptocurrencyRepository.getCryptoCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver)
    }
}