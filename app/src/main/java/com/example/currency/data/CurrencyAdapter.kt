package com.example.characters.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyBd
import com.example.currency.databinding.ItemCurrencuSetingBinding
import com.example.currency.databinding.ItemCurrensyBinding
import java.util.*

//class CurrencyAdapter(
//    private val characterList: MutableList<CurrencyBd> = mutableListOf(),
//
//    ) : RecyclerView.Adapter<CurrencyAdapter.CharacterViewHolder>() {
//
//
//    override fun getItemCount(): Int {
//        return characterList.size
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
//        CharacterViewHolder(
//            ItemCurrensyBinding.inflate(LayoutInflater.from(parent.context))
//        )
//
//
//    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
//        holder.bind(characterList[position])
//    }
//
//    fun update(newCharacterList: List<CurrencyBd>) {
//        characterList.clear()
//        characterList.addAll(newCharacterList)
//        notifyDataSetChanged()
//
//    }
//
//    class CharacterViewHolder(
//        private val bindingView: ItemCurrensyBinding,
//
//        ) : RecyclerView.ViewHolder(bindingView.root) {
//
//
//        fun bind(item: CurrencyBd) {
//
//            bindingView.tvName.text = item.name
//            bindingView.tvCharCod.text = item.charCode
//            bindingView.tvRate.text = item.rate.toString()
//
//        }
//    }
//}

class CurrencyAdapter(

) : ListAdapter<CurrencyBd, CurrencyAdapter.CurrencyViewHolder2>(DiffUtilItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder2 =
        CurrencyViewHolder2(
            ItemCurrensyBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: CurrencyViewHolder2, position: Int) {
        holder.bind(getItem(position))
    }

    class CurrencyViewHolder2(
        private val bindingView: ItemCurrensyBinding,

        ) : RecyclerView.ViewHolder(bindingView.root) {


        fun bind(item: CurrencyBd) {

            bindingView.tvName.text = item.name
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvRate.text = item.rate.toString()
            bindingView.tvRate1.text = item.rate.toString()
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
