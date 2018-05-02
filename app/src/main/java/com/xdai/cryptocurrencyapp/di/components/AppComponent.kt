package com.xdai.cryptocurrencyapp.di.components

import android.app.Application
import com.xdai.cryptocurrencyapp.CryptoCurrencyApplication
import com.xdai.cryptocurrencyapp.di.modules.AppModule
import com.xdai.cryptocurrencyapp.di.modules.BuildersModule
import com.xdai.cryptocurrencyapp.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class,
                NetworkModule::class)
)
interface AppComponent {
    fun inject(app: CryptoCurrencyApplication)
}