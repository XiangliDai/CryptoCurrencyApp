package com.xdai.cryptocurrencyapp.di.modules

import android.arch.lifecycle.ViewModel
import com.xdai.cryptocurrencyapp.ui.MainActivity
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModel
import com.xdai.cryptocurrencyapp.viewModels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity


    @Binds
    @IntoMap
    @ViewModelKey(CryptocurrenciesViewModel::class)
    abstract fun bindMainViewModel(viewModel: CryptocurrenciesViewModel): ViewModel
}