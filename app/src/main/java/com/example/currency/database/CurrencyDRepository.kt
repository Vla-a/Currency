package com.example.currency.database

import com.example.characters.database.CurrencyEntity
import com.example.currency.data.CurrencyBd
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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
                    currencyEntity.rate,
                    currencyEntity.nam
                )
            }
        }

    suspend fun deleteCurrencu(currencyBd: CurrencyBd) {
        currencyDao.deleteCurrency(currencyBd.entity())
    }
}

    fun CurrencyBd.entity() = CurrencyEntity(
    this.id, this.numCod, this.charCode, this.scale, this.name, this.rate, this.nam
)