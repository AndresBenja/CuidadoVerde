package com.example.cuidadoverde.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidadoverde.models.PlantaResponse
import com.example.cuidadoverde.repository.ImplementPlanta
import kotlinx.coroutines.launch

class ListadodePlantaEspecificoViewModel: ViewModel() {
    private val repositoryPlanta = ImplementPlanta()
    private val plantaespecifica = MutableLiveData<PlantaResponse>()

    val plantaespecifico_ld: LiveData<PlantaResponse>
        get() = plantaespecifica


    fun traerplantaespecifica(idplantaespecifica: Int) {

        viewModelScope.launch {
            var plantaespecial = repositoryPlanta.traerplantaespecifica(idplantaespecifica)
            plantaespecifica.value=plantaespecial
        }

    }

}