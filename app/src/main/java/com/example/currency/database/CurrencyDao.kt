package com.example.currency.database


import androidx.room.*
import com.example.characters.database.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM character_table")
    fun getCharacterList(): Flow<MutableList<CurrencyEntity>>

    @Query("SELECT * FROM character_table WHERE nam LIKE :nam ORDER BY id")
    fun getCurrencyList(nam: String): Flow<List<CurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCyrrency(currency:List<CurrencyEntity>)

    @Delete
    suspend fun deleteCurrency(currency: CurrencyEntity)

}