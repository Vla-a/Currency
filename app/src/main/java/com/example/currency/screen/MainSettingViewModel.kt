package com.example.currency.screen


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyResult
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
    val listPair:MutableList<Pair<String,Double>> = mutableListOf()
    val nameListLiveDataYeasDay: MutableLiveData<MutableList<CurrencyResult>> = MutableLiveData()

//    val listLiveData: LiveData<List<Currency>> =
//        cRepository.getList() .map {
//            it.map { currencyEntity ->
//
//                Currency(
//                    currencyEntity.id,
//                    currencyEntity.numCod,
//                    currencyEntity.charCode,
//                    currencyEntity.scale,
//                    currencyEntity.name,
//                    currencyEntity.rate,
//                    currencyEntity.nam
//                )
//            }
//        }.asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            nameListLiveDataYeasDay.postValue(cRepository.getCurrenciesResult())
            Log.e("KEK",LocalDate.now().plus(-1, ChronoUnit.DAYS).toString())
    //        nameListLiveDataYeasDay.postValue(getCurrensyTommorowOrYeastoday())
        //    Log.e("KEK", cRepository.getCurrenciesListYesDay().toString())

        }
    }

    fun addCurency(currencyList: MutableList<CurrencyResult>) {

        viewModelScope.launch {
            cRepository.addCurrency(currencyList)
        }
    }
}

