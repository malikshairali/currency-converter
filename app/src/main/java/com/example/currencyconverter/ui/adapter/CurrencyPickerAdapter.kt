package com.example.currencyconverter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.google.android.material.button.MaterialButton

class CurrencyPickerAdapter(
    private val currencies: Array<String>,
    private var selectedCurrency: String
) : RecyclerView.Adapter<CurrencyPickerAdapter.ViewHolder>() {

    private var onItemClickListener: ((currency: String) -> Unit)? = null

    override fun getItemCount() = currencies.size

    fun setOnItemClickListener(listener: (currency: String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnCurrency.text = currencies[position]
        holder.btnCurrency.setIconTintResource(
            if (selectedCurrency == currencies[position]) {
                R.color.black
            } else {
                R.color.white
            }
        )
        holder.btnCurrency.setOnClickListener {
            selectedCurrency = currencies[position]
            onItemClickListener?.invoke(selectedCurrency)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val btnCurrency: MaterialButton

        init {
            btnCurrency = view.findViewById(R.id.btn_currency)
        }
    }
}