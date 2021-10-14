package com.example.currency.screen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.currency.data.Currency
import com.example.currency.restApi.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
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
    val nameListLiveDay: MutableLiveData<MutableList<Currency>> = MutableLiveData()
    val nameListLiveDataYeasDay: MutableLiveData<MutableList<Currency>> = MutableLiveData()

    val listLiveData: LiveData<List<Currency>> =
        cRepository.getList() .map {
            it.map { currencyEntity ->

                Currency(
                    currencyEntity.id,
                    currencyEntity.numCod,
                    currencyEntity.charCode,
                    currencyEntity.scale,
                    currencyEntity.name,
                    currencyEntity.rate,
                    currencyEntity.nam
                )
            }
        }.asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            nameListLiveDataYeasDay.postValue(cRepository.getCurrenciesListDay(toDay))
            nameListLiveDataYeasDay.postValue(cRepository.getCurrenciesListDay(yeasDay))
            // Log.e("KEK", cRepository.getCurrenciesListTommorow().toString())
        }
    }

    fun addCurency(currencyList: MutableList<Currency>) {

        viewModelScope.launch {
            cRepository.addCurrency(currencyList)
        }
    }

}

