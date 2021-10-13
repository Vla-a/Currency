package com.example.currency.restApi

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.characters.database.CurrencyEntity
import com.example.characters.restApi.CurrencyApi
import com.example.currency.data.Currency
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
                    nam = false
                )
            }
        } as MutableList<Currency>
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addCurrency(currency: CurrencyEntity) {
        currencyDao.addCyrrency(currency)
    }

    suspend fun deleteCurrency(currency: Currency) {

        currencyDao.deleteCurrency(currency.entity())
    }

    private fun Currency.entity() = CurrencyEntity(
        this.id, this.numCod, this.charCode, this.scale, this.name, this.rate
    )

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
                    nam = false
                )
            }
        } as MutableList<Currency>
    }
}