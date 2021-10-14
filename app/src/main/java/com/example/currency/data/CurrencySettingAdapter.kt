package com.example.currency.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.databinding.ItemCurrencuSetingBinding
import com.example.currency.sharedprefs.SharedPrefsKeys
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtils

class CurrencySettingAdapter(
    private val sweetList: MutableList<Currency>,
    private val itemAtach: (Currency) -> Unit
) : RecyclerView.Adapter<CurrencySettingAdapter.CurrencyViewHolder>() {

    lateinit var sharedPreferences: SharedPreferences

    override fun getItemCount(): Int = sweetList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            ItemCurrencuSetingBinding.inflate(LayoutInflater.from(parent.context)),
            itemAtach
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(sweetList[position])

//   sharedPreferences.edit().putInt(sweetList[position].charCode, position)
//
//        if(sharedPreferences.getBoolean(sweetList[position].charCode, false)) {
//            holder.bindingView.ASwitch.isChecked = true
//        }
    }


    fun update(newSweetList: MutableList<Currency>) {
        sweetList.clear()
        sweetList.addAll(newSweetList)
        notifyDataSetChanged()
    }

    class CurrencyViewHolder(
        val bindingView: ItemCurrencuSetingBinding,
        private val itemAtach: (Currency) -> Unit
    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Currency) {
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvName.text = item.name

            if (item.charCode == "USD") {
                bindingView.ASwitch.isChecked = true
            }
            if (item.charCode == "EUR") {
                bindingView.ASwitch.isChecked = true
            }
            if (item.charCode == "RUB") {
                bindingView.ASwitch.isChecked = true
            }

//           if( SharedPrefsUtils.getBoolen(SharedPrefsKeys.PREFS_KEY) == null){
//               bindingView.ASwitch.isChecked = true
//           }else{
//               bindingView.ASwitch.isChecked = false
//           }

            bindingView.ASwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                   SharedPrefsUtils.putBoolen(SharedPrefsKeys.PREFS_KEY,  true)
                } else {
                    SharedPrefsUtils.putBoolen(SharedPrefsKeys.PREFS_KEY,  false)

                }
            }
            bindingView.noti.setOnLongClickListener {
                itemAtach(item)
                return@setOnLongClickListener true

            }

        }
    }
}


