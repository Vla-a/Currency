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

    var list = mutableListOf<Currency>()

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
        list = currencyApi.getCharacterListDay(toDay).map {
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
                        mist(it.charCode)

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
                        mist(it.charCode)
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
    private suspend fun mist(charCof: String): Double {


        when (charCof) {
            "AUD" -> {
                return list[0].rate
            }
            "AMD" -> {
                return list[1].rate
            }
            "BGN" -> {
                return list[2].rate
            }
            "UAH" -> {
                return list[3].rate
            }
            "DKK" -> {
                return list[4].rate
            }
            "USD" -> {
                return list[5].rate
            }
            "EUR" -> {
                return list[6].rate
            }
            "PLN" -> {
                return list[7].rate
            }
            "JPY" -> {
                return list[8].rate
            }
            "IRR" -> {
                return list[9].rate
            }
            "ISK" -> {
                return list[10].rate
            }
            "CAD" -> {
                return list[11].rate
            }
            "CNY" -> {
                return list[12].rate
            }
            "KWD" -> {
                return list[13].rate
            }
            "MDL" -> {
                return list[14].rate
            }
            "NZD" -> {
                return list[15].rate
            }
            "NOK" -> {
                return list[16].rate
            }
            "RUB" -> {
                return list[17].rate
            }
            "XDR" -> {
                return list[18].rate
            }
            "SGD" -> {
                return list[19].rate
            }
            "KGS" -> {
                return list[20].rate
            }
            "KZT" -> {
                return list[21].rate
            }
            "TRY" -> {
                return list[22].rate
            }
            "GBP" -> {
                return list[23].rate
            }
            "CZK" -> {
                return list[24].rate
            }
            "SEK" -> {
                return list[25].rate
            }
            "CHF" -> {
                return list[26].rate
            }

        }
        return list[26].rate
    }
}