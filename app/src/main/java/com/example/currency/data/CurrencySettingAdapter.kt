package com.example.currency.data

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.databinding.ItemCurrencuSetingBinding

class CurrencySettingAdapter(
    private val sweetList: MutableList<Currency>,
    private val itemSwich: (Currency) -> Unit,
    private val itemAtach: (Currency) -> Unit
) : RecyclerView.Adapter<CurrencySettingAdapter.CurrencyViewHolder>() {

    override fun getItemCount(): Int = sweetList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            ItemCurrencuSetingBinding.inflate(LayoutInflater.from(parent.context)),
            itemSwich,
            itemAtach
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(sweetList[position])
    }



    fun update(newSweetList: MutableList<Currency>) {
        sweetList.clear()
        sweetList.addAll(newSweetList)
        notifyDataSetChanged()
    }

    class CurrencyViewHolder(
        private val bindingView: ItemCurrencuSetingBinding,
        private val itemSwich: (Currency) -> Unit,
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

            if (bindingView.ASwitch.isChecked == true) {
                item.nam = true
            }


            bindingView.ASwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    itemSwich(item)
                }

            }
            bindingView.noti.setOnLongClickListener {
                itemAtach(item)
                return@setOnLongClickListener true

            }
        }
    }
}



