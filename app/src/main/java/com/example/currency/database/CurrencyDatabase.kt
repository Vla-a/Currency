package com.example.currency.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.characters.database.CurrencyEntity


@Database(entities = [CurrencyEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun CurrencyDao(): CurrencyDao
}

object DatabaseConstructor {
    fun create(context: Context): CurrencyDatabase =
        Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "character_database"
        ).build()

}