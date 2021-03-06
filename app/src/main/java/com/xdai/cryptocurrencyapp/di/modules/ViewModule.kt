package com.xdai.cryptocurrencyapp.di.modules

import com.xdai.cryptocurrencyapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}