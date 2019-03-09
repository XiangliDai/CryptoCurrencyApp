package com.xdai.cryptocurrencyapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModel
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModelFactory
import com.xdai.cryptocurrencyapp.viewModels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelBuilderModule {
    @Binds
    @IntoMap
    @ViewModelKey(CryptocurrenciesViewModel::class)
    abstract fun bindMainViewModel(viewModel: CryptocurrenciesViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: CryptocurrenciesViewModelFactory): ViewModelProvider.Factory

}