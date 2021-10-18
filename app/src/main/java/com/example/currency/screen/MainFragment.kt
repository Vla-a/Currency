package com.example.currency.screen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.data.CurrencyAdapter
import com.example.characters.screen.MainSettingFragment
import com.example.characters.screen.MainSettingFragment.Companion.KEYDATE
import com.example.characters.screen.MainSettingFragment.Companion.NEWDATE
import com.example.characters.screen.MainViewModel
import com.example.currency.R
import com.example.currency.data.CurrencyBd
import com.example.currency.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
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

        val currencyAdapter = CurrencyAdapter() {
            delateCurrency(it)
        }

        if (context?.let { isOnline(it) } == false) {
            binding?.bSetting?.visibility = View.INVISIBLE
            Toast.makeText(context, R.string.No_currency, Toast.LENGTH_SHORT).show()
        }



        binding!!.rvCurrency.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding!!.rvCurrency?.adapter = currencyAdapter

        myViewModel.nameListLiveDataBd.observe(this.viewLifecycleOwner, Observer {list ->
            currencyAdapter.submitList(list)
            binding!!.bSetting.setOnClickListener {
                this.findNavController().navigate(MainFragmentDirections.toMain2Fragment())
           list.forEach { myViewModel.delateCurrency(it) }
            }
        })

        binding!!.toolbar.setOnClickListener {
            activity?.finish()
        }
        binding!!.today.text =
            SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(System.currentTimeMillis())

        setFragmentResultListener(NEWDATE) { key, bundle ->
val result = bundle.getString(KEYDATE)?.substring(0,10)?.replace("-",".",true)
            if (result == LocalDate.now().plus(1, ChronoUnit.DAYS)
                    .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))){
                binding?.tomorrow?.text = LocalDate.now().plus(1, ChronoUnit.DAYS)
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            }else{
                binding?.tomorrow?.text = LocalDate.now().plus(-1, ChronoUnit.DAYS)
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            }
        }
    }

    private fun delateCurrency(currencyBd: CurrencyBd) {
        myViewModel.delateCurrency(currencyBd)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}
