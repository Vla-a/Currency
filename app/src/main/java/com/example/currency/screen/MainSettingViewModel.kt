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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class MainSettingViewModel(
    private val cRepository: CurrencyRepository
) : ViewModel() {

        val tommorow = LocalDate.now().plus(1, ChronoUnit.DAYS)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val yeasDay = LocalDate.now().plus(-1, ChronoUnit.DAYS)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val toDay = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(System.currentTimeMillis())
    val nameListLiveData: MutableLiveData<MutableList<Currency>> = MutableLiveData()
    val nameListLiveData2: MutableLiveData<MutableList<Currency>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            nameListLiveData.postValue(cRepository.getCurrenciesListDay(toDay))
            nameListLiveData2.postValue(cRepository.getCurrenciesListDay(yeasDay))
            // Log.e("KEK", cRepository.getCurrenciesListTommorow().toString())
        }
    }

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            nameListLiveData.postValue(cRepository.getCurrenciesList())
            // Log.e("KEK", cRepository.getCurrenciesListTommorow().toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListDay(day: String) {
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
