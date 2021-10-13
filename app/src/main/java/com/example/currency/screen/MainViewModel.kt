package com.example.characters.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.currency.data.CurrencyBd
import com.example.currency.database.CurrencyDRepository
import com.example.currency.restApi.CurrencyRepository
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent


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

}