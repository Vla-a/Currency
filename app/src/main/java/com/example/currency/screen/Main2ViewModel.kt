package com.example.currency.screen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characters.database.CurrencyEntity
import com.example.currency.data.Currency
import com.example.currency.restApi.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class Main2ViewModel(
    private val cRepository: CurrencyRepository
) : ViewModel() {

    val toDay = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(System.currentTimeMillis())
    val nameListLiveData: MutableLiveData<MutableList<Currency>> = MutableLiveData()


    init {
        getListDay(toDay)

    }

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            nameListLiveData.postValue(cRepository.getCurrenciesList())
            // Log.e("KEK", cRepository.getCurrenciesListTommorow().toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListDay(day: String) {
        viewModelScope.launch(Dispatchers.IO) {
            nameListLiveData.postValue(cRepository.getCurrenciesListDay(day))
            // Log.e("KEK", cRepository.getCurrenciesListTommorow().toString())
        }
    }

    fun addCurrency(currency: Currency) {
        val newCurrency = CurrencyEntity(
            currency.id,
            currency.numCod,
            currency.charCode,
            currency.scale,
            currency.name,
            currency.rate
        )
        viewModelScope.launch {
            cRepository.addCurrency(newCurrency)
        }
    }
    fun deleteCurrenty(currency: Currency) {
        viewModelScope.launch {
            cRepository.deleteCurrency(currency)
        }
    }
}

