package com.example.currency.database


import androidx.room.*
import com.example.characters.database.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM character_table")
    fun getCharacterList(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM character_table WHERE name LIKE :name ORDER BY id")
    suspend fun getNameList(name: String): List<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCyrrency(currency: CurrencyEntity)

    @Delete
    suspend fun deleteCurrency(currency: CurrencyEntity)

}