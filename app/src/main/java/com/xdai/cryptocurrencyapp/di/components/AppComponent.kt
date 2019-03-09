package com.xdai.cryptocurrencyapp.di.components

import com.xdai.cryptocurrencyapp.CryptoCurrencyApplication
import com.xdai.cryptocurrencyapp.di.modules.AppModule
import com.xdai.cryptocurrencyapp.di.modules.ViewModule
import com.xdai.cryptocurrencyapp.di.modules.NetworkModule
import com.xdai.cryptocurrencyapp.di.modules.ViewModelBuilderModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidInjectionModule::class, ViewModule::class, AppModule::class, ViewModelBuilderModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(app: CryptoCurrencyApplication)
}