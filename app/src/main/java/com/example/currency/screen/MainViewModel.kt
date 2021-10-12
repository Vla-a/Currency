package com.example.characters.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.currency.data.Currency
import com.example.currency.restApi.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel(
    private val cRepository: CurrencyRepository
) : ViewModel() {

    val nameListLiveData: MutableLiveData<List<Currency>> = MutableLiveData()
    val nameListLiveDataTommorow: MutableLiveData<List<CurrencyRepository>> = MutableLiveData()

    init {

        getList()


        }


    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {

            nameListLiveData.postValue(cRepository.getCurrenciesList())
            Log.e("KEK", cRepository.getCurrenciesListTommorow().toString())
        }
    }
}

