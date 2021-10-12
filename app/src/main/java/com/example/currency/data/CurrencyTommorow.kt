package com.example.currency.data

data class CurrencyTommorow(
    val id: Long,
    val numCod: String,
    val charCode: String,
    val scale: Int,
    val name: String,
    val rate: Double
)