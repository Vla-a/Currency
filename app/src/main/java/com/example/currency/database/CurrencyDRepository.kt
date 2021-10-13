package com.example.currency.database

import com.example.characters.database.CurrencyEntity
import com.example.currency.data.CurrencyBd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CurrencyDRepository(
    private val currencyDao: CurrencyDao
) {


    fun getList(): Flow<List<CurrencyBd>> =
        currencyDao.getCharacterList().map {
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

        }

//    suspend fun deleteCurrency() {
//        currencyDao.deleteTable()
//    }
}

