package com.example.characters.restApi

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.currency.entities.CurrencyResponce
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.StringReader
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

interface CurrencyApi {

    @GET("/api/exrates/rates?periodicity=0")
    suspend fun getCharacterList(): List<CurrencyResponce>

    @RequiresApi(Build.VERSION_CODES.O)
    @GET("https://www.nbrb.by/api/exrates/rates?&periodicity=0")
    suspend fun getCharacterListTommorow(
        @Query("ondate") ondate: String = LocalDate.now().plus(1, ChronoUnit.DAYS)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    ): List<CurrencyResponce>

    companion object {
        private const val BASE_URL = "https://www.nbrb.by/"

        fun get(): CurrencyApi = Retrofit.Builder().baseUrl(BASE_URL)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build().create(CurrencyApi::class.java)
    }
}


