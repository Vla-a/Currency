package com.example.currency.restApi

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.characters.database.CurrencyEntity
import com.example.characters.restApi.CurrencyApi
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyResult
import com.example.currency.database.CurrencyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class CurrencyRepository(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApi
) {


    @RequiresApi(Build.VERSION_CODES.O)
    val tommorow = LocalDate.now().plus(1, ChronoUnit.DAYS)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    @RequiresApi(Build.VERSION_CODES.O)
    val yeasDay = LocalDate.now().plus(-1, ChronoUnit.DAYS)
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val toDay = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).format(System.currentTimeMillis())

    @RequiresApi(Build.VERSION_CODES.O)


    suspend fun addCurrency(currencyList: MutableList<CurrencyResult>) {
        currencyDao.addCyrrency(currencyList.map {
            CurrencyEntity(
                it.id,
                it.numCod,
                it.charCode,
                it.scale,
                it.name,
                it.rate,
                it.nam,
                it.rateNewDay
            )
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrenciesListDay(): MutableList<Currency> {

        return withContext(Dispatchers.IO) {
            currencyApi.getCharacterListDay(toDay).map {
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


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrenciesResult(): MutableList<CurrencyResult> {
        val listTommorow = currencyApi.getCharacterListDay(tommorow)
        val list = currencyApi.getCharacterListDay(toDay).map {
            Currency(
                it.id,
                it.numCod,
                it.charCode,
                it.scale,
                it.name,
                it.rate,
                nam = ist(it.charCode)
            )

        } as MutableList<Currency>

        if (listTommorow.isNotEmpty()) {

            return withContext(Dispatchers.IO) {

                currencyApi.getCharacterListDay(tommorow).map {

                    CurrencyResult(
                        it.id,
                        it.numCod,
                        it.charCode,
                        it.scale,
                        it.name,
                        it.rate,
                        nam = ist(it.charCode),
                        mist(list)

                    )
                }
            } as MutableList<CurrencyResult>
        } else {
            return withContext(Dispatchers.IO) {

                currencyApi.getCharacterListDay(yeasDay).map {

                    CurrencyResult(
                        it.id,
                        it.numCod,
                        it.charCode,
                        it.scale,
                        it.name,
                        it.rate,
                        nam = ist(it.charCode),
                        mist(list)
                    )
                }
            } as MutableList<CurrencyResult>
        }

    }


    private fun ist(charCod: String): String {

        if (charCod == "EUR" || charCod == "RUB" || charCod == "USD") return "true"
        return "false"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun mist(charCof: MutableList<Currency>): Double {

        charCof.forEach {
            when (it.charCode) {
                "AUD" -> {
                    return charCof[0].rate
                }
                "AMD" -> {
                    return charCof[1].rate
                }
                "BGN" -> {
                    return charCof[2].rate
                }
                "UAH" -> {
                    return charCof[3].rate
                }
                "DKK" -> {
                    return charCof[4].rate
                }
                "USD" -> {
                    return charCof[5].rate
                }
                "EUR" -> {
                    return charCof[6].rate
                }
                "PLN" -> {
                    return charCof[7].rate
                }
                "JPY" -> {
                    return charCof[8].rate
                }
                "IRR" -> {
                    return charCof[9].rate
                }
                "ISK" -> {
                    return charCof[10].rate
                }
                "CAD" -> {
                    return charCof[11].rate
                }
                "CNY" -> {
                    return charCof[12].rate
                }
                "KWD" -> {
                    return charCof[13].rate
                }
                "MDL" -> {
                    return charCof[14].rate
                }
                "NZD" -> {
                    return charCof[15].rate
                }
                "NOK" -> {
                    return charCof[16].rate
                }
                "RUB" -> {
                    return charCof[17].rate
                }
                "XDR" -> {
                    return charCof[18].rate
                }
                "SGD" -> {
                    return charCof[19].rate
                }
                "KGS" -> {
                    return charCof[20].rate
                }
                "KZT" -> {
                    return charCof[21].rate
                }
                "TRY" -> {
                    return charCof[22].rate
                }
                "GBP" -> {
                    return charCof[23].rate
                }
                "CZK" -> {
                    return charCof[24].rate
                }
                "SEK" -> {
                    return charCof[25].rate
                }
                "CHF" -> {
                    return charCof[26].rate
                }
            }
        }
        return getCurrenciesListDay()[0].rate
    }
}