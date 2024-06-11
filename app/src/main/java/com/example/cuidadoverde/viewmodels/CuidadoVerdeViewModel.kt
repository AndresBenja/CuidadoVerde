package com.example.cuidadoverde.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidadoverde.models.PlantaResponse
import com.example.cuidadoverde.repository.ImplementPlanta
import kotlinx.coroutines.launch

class CuidadoVerdeViewModel : ViewModel() {
    private val repositoryPlanta=ImplementPlanta()
    private val mutablelCuidadoVerde = MutableLiveData<MutableList<PlantaResponse>>()
    val listadoplantas_ld: LiveData<MutableList<PlantaResponse>>
        get() = mutablelCuidadoVerde

init {
    viewModelScope.launch {
        mutablelCuidadoVerde.value=repositoryPlanta.traertodaslasplantas()
    }
}
}