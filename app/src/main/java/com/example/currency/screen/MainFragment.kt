package com.example.characters.screen

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.data.CurrencyAdapter
import com.example.currency.data.Currency
import com.example.currency.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class MainFragment : Fragment() {

    private val myViewModel: MainViewModel by viewModel()
    var binding: FragmentMainBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var listCharacter: MutableList<Currency> = mutableListOf()
        val currencyAdapter = CurrencyAdapter(listCharacter)

        binding!!.rvCurrency.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rvCurrency?.adapter = currencyAdapter

        myViewModel.nameListLiveData.observe(this.viewLifecycleOwner, Observer {
            currencyAdapter.update(it)
        })

        binding!!.today.text =
            SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(System.currentTimeMillis())
        binding!!.tomorrow.text = LocalDate.now().plus(1, ChronoUnit.DAYS)
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        binding?.bSetting?.setOnClickListener {
                this.findNavController().navigate(MainFragmentDirections.toMain2Fragment())
        }
    }


}
