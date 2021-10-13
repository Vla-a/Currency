package com.example.characters.screen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.data.CurrencyAdapter2
import com.example.currency.databinding.FragmentMain2Binding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.currency.R
import com.example.currency.data.Currency
import com.example.currency.screen.Main2ViewModel
import com.example.currency.sharedprefs.SharedPrefsKeys.PREFS_BRAND_KEY
import com.example.currency.sharedprefs.SharedPrefsUtils
import java.util.*

class Main2Fragment : Fragment() {

    private var list: MutableList<Currency> = mutableListOf()
    private val myViewModel: Main2ViewModel by viewModel()
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

        val currencyAdapter = CurrencyAdapter2(
            list,
            ::itemSwich,
            ::itemAtach
        )
//                      addCurrensy(it)
//            }

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

        binding!!.bSetting.setOnClickListener {

        }

        drapAndDrop.attachToRecyclerView(binding?.rvCurrencyS)

        binding!!.rvCurrencyS.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rvCurrencyS?.adapter = currencyAdapter

        myViewModel.nameListLiveData.observe(this.viewLifecycleOwner, Observer { it ->
            it.forEach {

                if (it.nam == true) {
              addCurrensy(it)
                }
            }
            currencyAdapter.update(it)
        })


        binding!!.toolbar.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun itemSwich(currency: Currency): Boolean {
        addCurrensy(currency)
        return true
    }

    private fun itemAtach(currency: Currency) {


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addCurrensy(currency: Currency) {
        myViewModel.addCurrency(currency)
    }
}


