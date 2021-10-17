package com.example.characters.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.currency.data.CurrencyBd
import com.example.currency.database.CurrencyDRepository
import com.example.currency.restApi.CurrencyRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent


@KoinApiExtension
class MainViewModel(
    private val cRepository: CurrencyRepository,
    private val cRepositoryBd: CurrencyDRepository
) : ViewModel(), KoinComponent {

    val nameListLiveDataBd: LiveData<List<CurrencyBd>> =
        cRepositoryBd.getCurrencyList().map {
            it.map { currencyEntity ->

                CurrencyBd(
                    currencyEntity.id,
                    currencyEntity.numCod,
                    currencyEntity.charCode,
                    currencyEntity.scale,
                    currencyEntity.name,
                    currencyEntity.rate,
                    currencyEntity.nam,
                    currencyEntity.rateNewDay
                )
            }
        }.asLiveData()

//init {
//    viewModelScope.launch {
//        cRepository.addCurrency()
//    }
//}

    fun delateCurrency(currencyBd: CurrencyBd) {
        viewModelScope.launch {
            cRepositoryBd.deleteCurrencu(currencyBd)
        }
    }

}

