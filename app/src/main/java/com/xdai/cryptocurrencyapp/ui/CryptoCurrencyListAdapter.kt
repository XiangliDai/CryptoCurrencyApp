package com.xdai.cryptocurrencyapp.ui

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xdai.cryptocurrencyapp.R
import com.xdai.cryptocurrencyapp.databinding.CurrencyItemBinding
import com.xdai.cryptocurrencyapp.models.CryptoCurrency


class CryptoCurrencyListAdapter(private val context: Context, cryptoCurrencies: List<CryptoCurrency>?) : RecyclerView.Adapter<CryptoCurrencyListAdapter.CryptoCurrencyViewHolder>() {
    private var cryptoCurrencyList = ArrayList<CryptoCurrency>()
    lateinit var binding: CurrencyItemBinding

    init {
        cryptoCurrencyList = cryptoCurrencies as ArrayList<CryptoCurrency>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrencyViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.currency_item, parent, false)

        //val itemView = LayoutInflater.from(context).inflate(R.layout.currency_item, parent,false)
        return CryptoCurrencyViewHolder(binding.root)

    }

    override fun getItemCount(): Int {
        return cryptoCurrencyList.size
    }

    override fun onBindViewHolder(holder: CryptoCurrencyViewHolder, position: Int) {
        val cryptoCurrency = cryptoCurrencyList[position]
        binding.cryptoCurrency = cryptoCurrency
        binding.executePendingBindings()

        //holder?.bindItem(cryptoCurrency)
    }


    class CryptoCurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /* var name = itemView.findViewById<TextView>(R.id.name)
        var price_usd = itemView.findViewById<TextView>(R.id.price_usd)
        var price_btc = itemView.findViewById<TextView>(R.id.price_btc)
        var symbol = itemView.findViewById<TextView>(R.id.symbol)
        fun bindItem(cryptoCurrencyryptoCurrency){
            val current_symbol = cryptoCurrency.symbol
            name.text = cryptoCurrency.name
            symbol.text = "($current_symbol)"
            price_usd.text = cryptoCurrency.priceUsd.toString()
            price_btc.text = cryptoCurrency.priceBtc
        }*/
    }
}