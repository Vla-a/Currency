package com.example.currency.restApi

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.characters.database.CurrencyEntity
import com.example.characters.restApi.CurrencyApi
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyBd
import com.example.currency.database.CurrencyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepository(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApi
) {

    suspend fun getCurrenciesList(): MutableList<Currency> {

        return withContext(Dispatchers.IO) {
            currencyApi.getCharacterList().map {
                Currency(
                    it.id,
                    it.numCod,
                    it.charCode,
                    it.scale,
                    it.name,
                    it.rate,
                    nam = ist(it.charCode)
                )
            }
        } as MutableList<Currency>
    }

    private fun ist(charCof: String): Boolean {

        if (charCof == "EUR" || charCof == "RUB" || charCof == "USD") return true
        return false
    }

    suspend fun addCurrency(currencyList: MutableList<Currency>) {
        currencyDao.addCyrrency(currencyList.map {
            CurrencyEntity(
                it.id,
                it.numCod,
                it.charCode,
                it.scale,
                it.name,
                it.rate,
                it.nam.toString()
            )
        })

    }

//
//    suspend fun deleteCurrency(currencyList: MutableList<Currency>) {
//
//        currencyDao.deleteCurrency(currencyList.map {
//            CurrencyEntity(
//                it.id,
//                it.numCod,
//                it.charCode,
//                it.scale,
//                it.name,
//                it.rate,
//                it.nam.toString()
//            )
//        })
//    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrenciesListDay(day: String): MutableList<Currency> {

        return withContext(Dispatchers.IO) {
            currencyApi.getCharacterListDay(day).map {
                Currency(
                    it.id,
                    it.numCod,
                    it.charCode,
                    it.scale,
                    it.name,
                    it.rate,
                    nam = ist(it.charCode)
                )
            }
        } as MutableList<Currency>
    }
}