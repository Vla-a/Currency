package com.example.characters.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyBd
import com.example.currency.database.CurrencyDRepository
import com.example.currency.restApi.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


@KoinApiExtension
class MainViewModel(
    private val cRepository: CurrencyRepository,
    private val cRepositoryBd: CurrencyDRepository
) : ViewModel(), KoinComponent {

//    val toDay = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(System.currentTimeMillis())
//    val tommorow = LocalDate.now().plus(1, ChronoUnit.DAYS)
//        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//    val yeasDay = LocalDate.now().plus(-1, ChronoUnit.DAYS)
//        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//    var counter = 0

    val nameListLiveDataBd: LiveData<List<CurrencyBd>> =
        cRepositoryBd.getList().map {
            it.map { currencyEntity ->

                CurrencyBd(
                    currencyEntity.id,
                    currencyEntity.numCod,
                    currencyEntity.charCode,
                    currencyEntity.scale,
                    currencyEntity.name,
                    currencyEntity.rate
                )
            }
        }.asLiveData()

//    fun deleteCurrency() {
//        viewModelScope.launch {
//            cRepositoryBd.deleteCurrency()
//        }
//    }
}