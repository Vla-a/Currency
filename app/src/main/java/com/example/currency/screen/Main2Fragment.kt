package com.example.characters.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyAdapter2
import com.example.currency.databinding.FragmentMain2Binding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer

class Main2Fragment : Fragment() {

    private val myViewModel: MainViewModel by viewModel()
    private var binding: FragmentMain2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMain2Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            var listCharacter: MutableList<Currency> = mutableListOf()
            val currencyAdapter = CurrencyAdapter2(listCharacter)

            binding!!.rvCurrencyS.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding!!.rvCurrencyS?.adapter = currencyAdapter

            myViewModel.nameListLiveData.observe(this.viewLifecycleOwner, Observer {
                currencyAdapter.update(it)
            })


                binding!!.toolbar.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }
}