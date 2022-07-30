package com.natiqhaciyef.kotlincryptocurrencyapp.service

import com.natiqhaciyef.kotlincryptocurrencyapp.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface CryptoAPI {
    //Get, Post, Update, Delete
    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>
}