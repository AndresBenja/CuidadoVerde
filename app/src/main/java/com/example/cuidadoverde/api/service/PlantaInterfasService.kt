package com.example.cuidadoverde.api.service

import com.example.cuidadoverde.models.PlantaResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantaInterfasService {
    //aqui se trae al listado de usuario de la API
    @GET("plantas")
    suspend fun traertodaslasplantas():MutableList<PlantaResponse>

// aqui se trae a un usuario especifico

    @GET("plantas/{id}")
    suspend fun traerplantaespecifica(@Path("id")idplanta:Int):PlantaResponse

}