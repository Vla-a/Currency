package com.example.characters.screen

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.R
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyResult
import com.example.currency.data.CurrencySettingAdapter
import com.example.currency.databinding.FragmentMain2Binding
import com.example.currency.screen.MainSettingViewModel
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtilss
import kotlinx.coroutines.NonDisposableHandle.parent


import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainSettingFragment : Fragment() {

    private var list: MutableList<CurrencyResult> = mutableListOf()
    private val myViewModel: MainSettingViewModel by viewModel()
    private var binding: FragmentMain2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMain2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       SharedPrefsUtilss.sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        val currencyAdapter = CurrencySettingAdapter(list)

        val drapAndDrop = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                Collections.swap(list, fromPosition, toPosition)
                currencyAdapter.notifyItemMoved(fromPosition, toPosition)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        })


        drapAndDrop.attachToRecyclerView(binding?.rvCurrencyS)

        binding!!.rvCurrencyS.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rvCurrencyS?.adapter = currencyAdapter



        myViewModel.nameListLiveDataYeasDay.observe(this.viewLifecycleOwner, Observer { list ->

            currencyAdapter.update(list as MutableList<CurrencyResult>)

            binding!!.bSetting.setOnClickListener {
                val listR = mutableListOf<CurrencyResult>()

                list.forEach {
                    if(it.nam == "true") listR.add(it)
                }
                addCurrensy(listR)
                it.findNavController().popBackStack()
            }
        })
        binding!!.toolbar.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addCurrensy(currency: MutableList<CurrencyResult>) {
        myViewModel.addCurency(currency)
    }
}


