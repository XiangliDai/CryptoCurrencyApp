package com.xdai.cryptocurrencyapp.di.modules

import android.arch.lifecycle.ViewModelProvider
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilderModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: CryptocurrenciesViewModelFactory): ViewModelProvider.Factory

}