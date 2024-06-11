package com.example.cuidadoverde.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidadoverde.models.PlantaResponse
import com.example.cuidadoverde.repository.ImplementPlanta
import kotlinx.coroutines.launch

class ListadodePlantasGeneralViewModel : ViewModel() {
    private val repositoryPlanta=ImplementPlanta()
    private val mutablelistPlanta= MutableLiveData<MutableList<PlantaResponse>>()
    val listadoplanta_ld: LiveData<MutableList<PlantaResponse>>
        get()=mutablelistPlanta

    init {
        viewModelScope.launch {
            mutablelistPlanta.value=repositoryPlanta.traertodaslasplantas()
        }

    }


}