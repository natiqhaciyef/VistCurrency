package com.natiqhaciyef.kotlincryptocurrencyapp.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
    //@SerializedName("currency")
    val currency: String ,
    //@SerializedName("currency")
    val price : String
    )
