package com.example.characters.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.data.CurrencyBd
import com.example.currency.databinding.ItemCurrensyBinding


class CurrencyAdapter(
    private val delate: (CurrencyBd) -> Unit
) : ListAdapter<CurrencyBd, CurrencyAdapter.CurrencyViewHolder2>(DiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder2 =
        CurrencyViewHolder2(
            ItemCurrensyBinding.inflate(LayoutInflater.from(parent.context)), delate
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder2, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder2(
        private val bindingView: ItemCurrensyBinding,
        private val delate: (CurrencyBd) -> Unit
    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: CurrencyBd) {

            bindingView.tvName.text = item.name
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvRate.text = item.rateNewDay.toString()
            bindingView.tvRate1.text = item.rate.toString()

            itemView.setOnLongClickListener() {
                delate(item)
                true
            }
        }
    }
}

class DiffUtilItemCallback : DiffUtil.ItemCallback<CurrencyBd>() {
    override fun areItemsTheSame(oldItem: CurrencyBd, newItem: CurrencyBd): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CurrencyBd, newItem: CurrencyBd): Boolean {
        return oldItem.id == newItem.id
                && oldItem.name == newItem.name
                && oldItem.charCode == newItem.charCode
                && oldItem.rate == newItem.rate
                && oldItem.numCod == newItem.numCod
                && oldItem.scale == newItem.scale
    }
}
