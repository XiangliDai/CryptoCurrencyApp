package com.xdai.cryptocurrencyapp

import android.app.Activity
import android.app.Application
import com.xdai.cryptocurrencyapp.di.components.DaggerAppComponent
import com.xdai.cryptocurrencyapp.di.modules.AppModule
import com.xdai.cryptocurrencyapp.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoCurrencyApplication: Application(), HasActivityInjector  {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(getString(R.string.base_url)))
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}