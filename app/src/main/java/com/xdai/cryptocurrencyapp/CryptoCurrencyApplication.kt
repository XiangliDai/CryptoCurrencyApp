package com.xdai.cryptocurrencyapp

import android.app.Activity
import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import com.xdai.cryptocurrencyapp.di.components.DaggerAppComponent
import com.xdai.cryptocurrencyapp.di.modules.AppModule
import com.xdai.cryptocurrencyapp.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoCurrencyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(getString(R.string.base_url)))
                .build()
                .inject(this)

        FirebaseApp.initializeApp(this)
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token
                    Log.d(TAG, "getInstanceId token $token")

                })
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    companion object {
        private const val TAG = "CryptoCurrency"
    }
}