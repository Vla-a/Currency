package com.example.currency.data

data class CurrencyResult(
    val id: Long,
    val numCod: String,
    var charCode: String,
    val scale: Int,
    val name: String,
    val rate: Double,
    var nam: String,
    val rateNewDay: Double
)