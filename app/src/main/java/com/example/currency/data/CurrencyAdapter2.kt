package com.example.currency.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.data.CurrencyAdapter
import com.example.currency.databinding.ItemCurrencuSetingBinding
import com.example.currency.databinding.ItemCurrensyBinding

class CurrencyAdapter2(
    private val currencyList: MutableList<Currency> = mutableListOf(),

    ) : RecyclerView.Adapter<CurrencyAdapter2.CurrencyViewHolder>() {


    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyAdapter2.CurrencyViewHolder =
        CurrencyViewHolder(
            ItemCurrencuSetingBinding.inflate(LayoutInflater.from(parent.context))
        )


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    fun update(newCharacterList: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newCharacterList)
        notifyDataSetChanged()

    }

    class CurrencyViewHolder(
        private val bindingView: ItemCurrencuSetingBinding,

        ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Currency) {
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvName.text = item.name
            bindingView.ASwitch.isChecked

        }
    }
}