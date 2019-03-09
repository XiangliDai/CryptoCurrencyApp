package com.xdai.cryptocurrencyapp.ui

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xdai.cryptocurrencyapp.R
import com.xdai.cryptocurrencyapp.databinding.CurrencyItemBinding
import com.xdai.cryptocurrencyapp.models.CryptoCurrency


class CryptoCurrencyListAdapter(private val context: Context, private val cryptoCurrencies: List<CryptoCurrency>?) : RecyclerView.Adapter<CryptoCurrencyListAdapter.CryptoCurrencyViewHolder>() {
    lateinit var binding: CurrencyItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrencyViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.currency_item, parent, false)
        return CryptoCurrencyViewHolder(binding.root)

    }

    override fun getItemCount(): Int {
        return cryptoCurrencies?.size ?: 0
    }

    override fun onBindViewHolder(holder: CryptoCurrencyViewHolder, position: Int) {
        val cryptoCurrency = cryptoCurrencies?.getOrNull(position)
        binding.cryptoCurrency = cryptoCurrency
        binding.executePendingBindings()
    }

    class CryptoCurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}