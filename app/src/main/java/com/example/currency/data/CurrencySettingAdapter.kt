package com.example.currency.data

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.databinding.ItemCurrencuSetingBinding
import com.example.currency.sharedprefs.SharedPrefsKeys
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtils
import java.util.*

class CurrencySettingAdapter(
     val currencyList: MutableList<Currency>

) : RecyclerView.Adapter<CurrencySettingAdapter.CurrencyViewHolder>() {

    companion object {
        private const val ITEM = 0
    }

    override fun getItemCount(): Int = currencyList.size

    override fun getItemViewType(position: Int) = ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            ItemCurrencuSetingBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])

//   sharedPreferences.edit().putInt(sweetList[position].charCode, position)
//
//        if(sharedPreferences.getBoolean(sweetList[position].charCode, false)) {
//            holder.bindingView.ASwitch.isChecked = true
//        }
    }


    fun update(newSweetList: MutableList<Currency>) {
        currencyList.clear()
        currencyList.addAll(newSweetList)
        notifyDataSetChanged()
    }

    fun addAll(newCurrenciesSettings: List<Currency>) {
        currencyList.addAll(newCurrenciesSettings)
        notifyDataSetChanged()
    }

    fun onItemMove(sourcePosition: Int, targetPosition: Int) {
        Collections.swap(currencyList, sourcePosition, targetPosition)
        notifyItemMoved(sourcePosition, targetPosition)
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
//
//            bindingView.ASwitch.setOnCheckedChangeListener { _, isChecked ->
//                if (isChecked) {
//                    SharedPrefsUtils.putBoolen(SharedPrefsKeys.PREFS_KEY, true)
//                } else {
//                    SharedPrefsUtils.putBoolen(SharedPrefsKeys.PREFS_KEY, false)
//
//                }
//            }
        }
    }
}