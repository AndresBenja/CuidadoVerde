package com.example.cuidadoverde.repository

import com.example.cuidadoverde.models.PlantaResponse

interface InterfasPlanta {
    suspend fun traertodaslasplantas(): MutableList<PlantaResponse>

    //los dos puntos son el retorno cuando esta afuera
    suspend fun traerplantaespecifica(plantaid: Int): PlantaResponse

}