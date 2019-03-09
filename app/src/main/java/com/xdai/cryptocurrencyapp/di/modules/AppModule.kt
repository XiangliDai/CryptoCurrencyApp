package com.xdai.cryptocurrencyapp.di.modules

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xdai.cryptocurrencyapp.dao.AppDatabase

import com.xdai.cryptocurrencyapp.dao.CryptoCurrenciesDao
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
    fun provideCryptoCurrenciesDatabase(app: Application): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, "cryptocurrencies_db")
            .build()

    @Provides
    @Singleton
    fun provideCryptoCurrenciesDao(database: AppDatabase): CryptoCurrenciesDao = database.cryptoCurrenciesDao()

    /*
    @Provides
    @Singleton
    fun provideCryptocurrenciesViewModelFactory(factory: CryptocurrenciesViewModelFactory): ViewModelProvider.Factory = factory
*/

}