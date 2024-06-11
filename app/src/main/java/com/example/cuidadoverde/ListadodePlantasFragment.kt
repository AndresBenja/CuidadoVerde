package com.example.cuidadoverde

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuidadoverde.databinding.FragmentListadodePlantasBinding
import com.example.cuidadoverde.models.PlantaResponse
import com.example.cuidadoverde.repository.ImplementPlanta
import com.example.cuidadoverde.viewmodels.CuidadoVerdeViewModel
import com.example.cuidadoverde.views.activitys.AdaptadorCuidadoVerde
import java.lang.ClassCastException


class ListadodePlantasFragment : Fragment() {

    private lateinit var binding: FragmentListadodePlantasBinding
    private lateinit var dobleclick: PlantasSelectListener
    private lateinit var listadoViewModel: CuidadoVerdeViewModel

    interface PlantasSelectListener {
        fun plantaSelect(planta: PlantaResponse)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dobleclick = try {
            context as PlantasSelectListener

        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement selectListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListadodePlantasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val jugadoreslist = getFakeListado()

        //llamar a la variable ViewModel con el ViewModel Provider
        listadoViewModel= ViewModelProvider(this).get(CuidadoVerdeViewModel::class.java)

        //aqui se instancia osea se llama el adaptador
        val adaptadorCuidadoVerde = AdaptadorCuidadoVerde()
        val recicla = binding.PlantasRecyclerView

        listadoViewModel.listadoplantas_ld.observe(viewLifecycleOwner) {


            //aqui le paso la lista que se invento

            adaptadorCuidadoVerde.plantalist = it
            recicla.layoutManager = LinearLayoutManager(requireContext())
            recicla.adapter = adaptadorCuidadoVerde
        }
        adaptadorCuidadoVerde.onItemClickListener = {
            dobleclick.plantaSelect(it)

        }


    }

}
