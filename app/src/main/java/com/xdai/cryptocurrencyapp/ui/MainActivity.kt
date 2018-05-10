package com.xdai.cryptocurrencyapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.xdai.cryptocurrencyapp.R
import com.xdai.cryptocurrencyapp.databinding.ActivityMainBinding
import com.xdai.cryptocurrencyapp.models.CryptoCurrency
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModel
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var cryptocurrenciesViewModel: CryptocurrenciesViewModel

    private var cryptoCurrencyList: MutableList<CryptoCurrency> = ArrayList()

    private var currencyListAdapter:CryptoCurrencyListAdapter = CryptoCurrencyListAdapter(this, cryptoCurrencyList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AndroidInjection.inject(this)
        initializeRecyclerView()
        cryptocurrenciesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CryptocurrenciesViewModel::class.java)
        binding.viewModel = cryptocurrenciesViewModel
        binding.executePendingBindings()
        cryptocurrenciesViewModel.loadCryptoCurrencies()
        cryptocurrenciesViewModel.cryptoCurrenciesResult().observe(this, Observer {
            //progress_bar.visibility = View.GONE

            binding.errorMessage.visibility = View.GONE
            if(it!= null && !it.isEmpty()){
                cryptoCurrencyList.clear()
                cryptoCurrencyList.addAll(it)
                currencyListAdapter.notifyDataSetChanged()
            }
        })

        cryptocurrenciesViewModel.cryptoCurrenciesError().observe(this, Observer {
            //progress_bar.visibility = View.GONE

            binding.apply{
                errorMessage.text = resources.getString(R.string.cryptocurrency_error_message) + it
                errorMessage.visibility = View.VISIBLE
            }

        })
    }

    private fun initializeRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = currencyListAdapter
            recyclerView.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        cryptocurrenciesViewModel.disposableObserver
        super.onDestroy()
    }
}
