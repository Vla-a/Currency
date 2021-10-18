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

//    @RequiresApi(Build.VERSION_CODES.O)
//    suspend fun getCurrenciesListTommorow(): MutableList<Currency> {
//
//        return withContext(Dispatchers.IO) {
//            currencyApi.getCharacterListDay(tommorow).map {
//                Currency(
//                    it.id,
//                    it.numCod,
//                    it.charCode,
//                    it.scale,
//                    it.name,
//                    it.rate,
//                    nam = ist(it.charCode)
//                )}
//        } as MutableList<Currency>
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)

//    suspend fun getCurrenciesListYesDay(): MutableList<Currency> {
//
//        return withContext(Dispatchers.IO) {
//            currencyApi.getCharacterListDay(yeasDay).map {
//                Currency(
//                    it.id,
//                    it.numCod,
//                    it.charCode,
//                    it.scale,
//                    it.name,
//                    it.rate,
//                    nam = ist(it.charCode)
//                )}
//        } as MutableList<Currency>
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getCurrenciesResult(): MutableList<CurrencyResult> {
val listTommorow = currencyApi.getCharacterListDay(tommorow)

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
        }else {
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
                return getCurrenciesListDay()[0].rate
            }
            "AMD" -> {
                return getCurrenciesListDay()[1].rate
            }
            "BGN" -> {
                return getCurrenciesListDay()[2].rate
            }
            "UAH" -> {
                return getCurrenciesListDay()[3].rate
            }
            "DKK" -> {
                return getCurrenciesListDay()[4].rate
            }
            "USD" -> {
                return getCurrenciesListDay()[5].rate
            }
            "EUR" -> {
                return getCurrenciesListDay()[6].rate
            }
            "PLN" -> {
                return getCurrenciesListDay()[7].rate
            }
            "JPY" -> {
                return getCurrenciesListDay()[8].rate
            }
            "IRR" -> {
                return getCurrenciesListDay()[9].rate
            }
            "ISK" -> {
                return getCurrenciesListDay()[10].rate
            }
            "CAD" -> {
                return getCurrenciesListDay()[11].rate
            }
            "CNY" -> {
                return getCurrenciesListDay()[12].rate
            }
            "KWD" -> {
                return getCurrenciesListDay()[13].rate
            }
            "MDL" -> {
                return getCurrenciesListDay()[14].rate
            }
            "NZD" -> {
                return getCurrenciesListDay()[15].rate
            }
            "NOK" -> {
                return getCurrenciesListDay()[16].rate
            }
            "RUB" -> {
                return getCurrenciesListDay()[17].rate
            }
            "XDR" -> {
                return getCurrenciesListDay()[18].rate
            }
            "SGD" -> {
                return getCurrenciesListDay()[19].rate
            }
            "KGS" -> {
                return getCurrenciesListDay()[20].rate
            }
            "KZT" -> {
                return getCurrenciesListDay()[21].rate
            }
            "TRY" -> {
                return getCurrenciesListDay()[22].rate
            }
            "GBP" -> {
                return getCurrenciesListDay()[23].rate
            }
            "CZK" -> {
                return getCurrenciesListDay()[24].rate
            }
            "SEK" -> {
                return getCurrenciesListDay()[25].rate
            }
            "CHF" -> {
                return getCurrenciesListDay()[26].rate
            }
        }
        return getCurrenciesListDay()[0].rate
    }
}