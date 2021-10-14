package com.example.currency.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.databinding.ItemCurrencuSetingBinding

class CurrencySettingAdapter(
    val currencyList: MutableList<Currency>

) : RecyclerView.Adapter<CurrencySettingAdapter.CurrencyViewHolder>() {

    companion object {
        private const val ITEM = 0
    }

    override fun getItemCount(): Int = currencyList.size

    override fun getItemViewType(position: Int) = ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder{

     return   CurrencyViewHolder(
            ItemCurrencuSetingBinding.inflate(LayoutInflater.from(parent.context)))
}

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    fun update(newSweetList: MutableList<Currency>) {
        currencyList.clear()
        currencyList.addAll(newSweetList)
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(
        val bindingView: ItemCurrencuSetingBinding

    ) : RecyclerView.ViewHolder(bindingView.root) {

        init {
            bindingView.ASwitch.setOnCheckedChangeListener { _, isChecked ->
                currencyList[adapterPosition].nam = isChecked
            }
        }

        fun bind(item: Currency) {
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvName.text = item.name
            bindingView.ASwitch.isChecked = item.nam

        }
    }
}