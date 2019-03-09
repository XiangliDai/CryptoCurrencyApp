package com.xdai.cryptocurrencyapp.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xdai.cryptocurrencyapp.models.CryptoCurrency
import com.xdai.cryptocurrencyapp.repositories.CryptocurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CryptocurrenciesViewModel @Inject constructor(private val cryptocurrencyRepository: CryptocurrencyRepository) : ViewModel() {


    private val cryptoCurrenciesResult: MutableLiveData<List<CryptoCurrency>> = MutableLiveData()
    private val cryptoCurrenciesError: MutableLiveData<String> = MutableLiveData()

    val result: LiveData<List<CryptoCurrency>>
        get() = cryptoCurrenciesResult
    val error: LiveData<String>
        get() = cryptoCurrenciesError

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var isLoading = ObservableField(true)


    init {
        loadCryptoCurrencies()
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun loadCryptoCurrencies() {
        isLoading.set(true)
        compositeDisposable.add(
                cryptocurrencyRepository.getCryptoCurrencies()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                Consumer { cryptoList ->
                                    isLoading.set(false)
                                    cryptoCurrenciesResult.value = cryptoList
                                },
                                Consumer { e ->
                                    isLoading.set(false)
                                    cryptoCurrenciesError.value = e.localizedMessage
                                })
        )
    }
}