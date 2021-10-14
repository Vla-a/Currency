package com.example.currency

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.characters.restApi.CurrencyApi
import com.example.characters.screen.MainViewModel
import com.example.currency.database.CurrencyDRepository
import com.example.currency.database.CurrencyDatabase
import com.example.currency.database.DatabaseConstructor
import com.example.currency.restApi.CurrencyRepository
import com.example.currency.screen.MainSettingViewModel
import com.example.currency.sharedprefs.SharedPrefsKeys
import com.example.myhomework.homework13.sharedprefs.SharedPrefsUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MySuperApp : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        SharedPrefsUtils.sharedPrefs =
            applicationContext.getSharedPreferences(SharedPrefsKeys.PREFS_KEY, MODE_PRIVATE)

         startKoin {
            androidContext(this@MySuperApp)
            modules(listOf(repositoryModule, viewModels, currencyApi, storageModule))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val viewModels = module {
        viewModel { MainViewModel(get(), get()) }
        viewModel { MainSettingViewModel(get()) }

    }

    private val repositoryModule = module {  //создаем репозитории

        factory { CurrencyRepository(get(), get()) }
        factory { CurrencyDRepository(get()) }
    }

    private val currencyApi = module {
        single { CurrencyApi.get() }
    }
    private val storageModule = module {
        single { DatabaseConstructor.create(get()) }  //создаем синглтон базы данных
        factory { get<CurrencyDatabase>().CurrencyDao() } //предоставляем доступ для конкретной Dao (в нашем случае NotesDao)

    }

}