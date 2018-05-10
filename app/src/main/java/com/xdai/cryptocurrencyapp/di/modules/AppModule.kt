package com.xdai.cryptocurrencyapp.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.xdai.cryptocurrencyapp.dao.CryptoCurrenciesDao
import com.xdai.cryptocurrencyapp.dao.Database
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideCryptoCurrenciesDatabase(app: Application): Database = Room.databaseBuilder(app, Database::class.java, "cryptocurrencies_db").build()

    @Provides
    @Singleton
    fun provideCryptoCurrenciesDao(database: Database): CryptoCurrenciesDao = database.cryptoCurrenciesDao()

    /*
    @Provides
    @Singleton
    fun provideCryptocurrenciesViewModelFactory(factory: CryptocurrenciesViewModelFactory): ViewModelProvider.Factory = factory
*/

}