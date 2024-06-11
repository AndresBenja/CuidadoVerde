package com.example.cuidadoverde.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit{

        return Retrofit.Builder().baseUrl("https://my-json-server.typicode.com/mauricioponce/TDApi/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
}