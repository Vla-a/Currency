package com.example.currency.restApi

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.characters.database.CurrencyEntity
import com.example.characters.restApi.CurrencyApi
import com.example.currency.data.Currency
import com.example.currency.data.CurrencyTommorow
import com.example.currency.database.CurrencyDao
import com.example.currency.entities.CurrencyResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CurrencyRepository(
    private val currencyDao: CurrencyDao,
    private val currencyApi: CurrencyApi
) {

    suspend  fun getCurrenciesList( ): MutableList<Currency> {

        return  withContext(Dispatchers.IO) {
            currencyApi.getCharacterList().map {
                Currency( it.id,
                    it.numCod,
                    it.charCode,
                    it.scale,
                    it.name,
                    it.rate)

            }
        }as MutableList<Currency>
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend  fun getCurrenciesListTommorow( ): MutableList<Currency> {

        return  withContext(Dispatchers.IO) {
            currencyApi.getCharacterListTommorow().map {
                CurrencyTommorow( it.id,
                    it.numCod,
                    it.charCode,
                    it.scale,
                    it.name,
                    it.rate)

            }
        }as MutableList<Currency>
    }

    suspend fun addCurrency(count: Long) {
        currencyDao.addCyrrency(
            withContext(Dispatchers.IO) {
                currencyApi.getCharacterList().map {
                    CurrencyEntity(
                        it.id,
                        it.numCod,
                        it.charCode,
                        it.scale,
                        it.name,
                        it.rate
                    )
                }
            } as MutableList<CurrencyEntity>
        )
    }

//    fun getCharacterList(): Flow<List<Currency>> =
//        currencyDao.getCharacterList().map { characterEntity ->
//            characterEntity.map {
//                Charac(it.id, it.name, it.status, it.species, it.image, it.type, it.gender, it.created)
//            }
//        }
}