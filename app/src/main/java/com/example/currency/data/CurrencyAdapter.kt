package com.example.characters.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.currency.data.Currency
import com.example.currency.databinding.ItemCurrensyBinding

class CurrencyAdapter(
    private val characterList: MutableList<Currency> = mutableListOf(),

) : RecyclerView.Adapter<CurrencyAdapter.CharacterViewHolder>() {


    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            ItemCurrensyBinding.inflate(LayoutInflater.from(parent.context))
        )


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    fun update(newCharacterList: List<Currency>) {
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()

    }

    class CharacterViewHolder(
        private val bindingView: ItemCurrensyBinding,

    ) : RecyclerView.ViewHolder(bindingView.root) {

        fun bind(item: Currency) {
            bindingView.tvCharCod.text = item.charCode
            bindingView.tvName.text = item.name
            bindingView.tvRate.text = item.rate.toString()

        }
    }
}

