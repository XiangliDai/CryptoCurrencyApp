package com.xdai.cryptocurrencyapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdai.cryptocurrencyapp.R
import com.xdai.cryptocurrencyapp.databinding.ActivityMainBinding
import com.xdai.cryptocurrencyapp.models.CryptoCurrency
import com.xdai.cryptocurrencyapp.viewModels.CryptocurrenciesViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: ActivityMainBinding

    lateinit var cryptocurrenciesViewModel: CryptocurrenciesViewModel
    private var cryptoCurrencyList: MutableList<CryptoCurrency> = mutableListOf()
    private var currencyListAdapter: CryptoCurrencyListAdapter = CryptoCurrencyListAdapter(this, cryptoCurrencyList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AndroidInjection.inject(this)
        initializeRecyclerView()
        cryptocurrenciesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CryptocurrenciesViewModel::class.java)
        binding.viewModel = cryptocurrenciesViewModel
        binding.executePendingBindings()

        cryptocurrenciesViewModel.result.observe(this, Observer {
            //progress_bar.visibility = View.GONE

            binding.errorMessage.visibility = View.GONE
            if (!it.isNullOrEmpty()) {
                cryptoCurrencyList.clear()
                cryptoCurrencyList.addAll(it)
                currencyListAdapter.notifyDataSetChanged()
            }
        })

        cryptocurrenciesViewModel.error.observe(this, Observer {
            //progress_bar.visibility = View.GONE

            binding.apply {
                errorMessage.text = resources.getString(R.string.cryptocurrency_error_message) + it
                errorMessage.visibility = View.VISIBLE
            }

        })
    }

    @SuppressLint("WrongConstant")
    private fun initializeRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.apply {
            recycler_view.layoutManager = linearLayoutManager
            recycler_view.adapter = currencyListAdapter
            recycler_view.setHasFixedSize(true)
        }
    }

}
