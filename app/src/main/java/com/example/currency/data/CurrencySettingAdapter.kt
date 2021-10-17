package com.example.currency.data

import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.databinding.ItemCurrencuSetingBinding
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtilss

class CurrencySettingAdapter(

    val currencyList: MutableList<CurrencyResult>
) : RecyclerView.Adapter<CurrencySettingAdapter.CurrencyViewHolder>() {

    companion object {
        private const val ITEM = 0
    }

    override fun getItemCount(): Int = currencyList.size

    override fun getItemViewType(position: Int) = ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder{
        SharedPrefsUtilss.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(parent.context)
     return   CurrencyViewHolder(
            ItemCurrencuSetingBinding.inflate(LayoutInflater.from(parent.context)))

}

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
        SharedPrefsUtilss.sharedPrefs.edit().putInt(currencyList[position].charCode, position)

        if(SharedPrefsUtilss.sharedPrefs.getBoolean(currencyList[position].charCode, false)) {
            holder.bindingView.ASwitch.isChecked = true
        }
    }

    fun update(newSweetList: MutableList<CurrencyResult>) {
        currencyList.clear()
        currencyList.addAll(newSweetList)
        notifyDataSetChanged()

    }

    inner class CurrencyViewHolder(
        val bindingView: ItemCurrencuSetingBinding

    ) : RecyclerView.ViewHolder(bindingView.root) {

        init {
            bindingView.ASwitch.setOnCheckedChangeListener { _, isChecked ->
                currencyList[adapterPosition].nam = isChecked.toString()
            }
        }

        fun bind(item: CurrencyResult) {
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvName.text = item.name
          bindingView.ASwitch.isChecked = item.nam == "true"

            bindingView.ASwitch.setOnCheckedChangeListener{buttonView, isChecked ->
                currencyList[adapterPosition].nam = isChecked.toString()
                if(isChecked){
                    SharedPrefsUtilss.sharedPrefs.edit().putBoolean(currencyList[adapterPosition].charCode, true).apply()
                } else {
                    SharedPrefsUtilss.sharedPrefs.edit().putBoolean(currencyList[adapterPosition].charCode, false).apply()
                }

            }
        }
    }
}