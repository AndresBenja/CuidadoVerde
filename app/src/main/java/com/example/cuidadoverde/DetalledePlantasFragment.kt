package com.example.cuidadoverde

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.cuidadoverde.databinding.FragmentDetalledePlantasBinding
import com.example.cuidadoverde.databinding.FragmentListadodePlantasBinding
import com.example.cuidadoverde.databinding.ItemdetalledeplantaBinding
import com.example.cuidadoverde.viewmodels.CuidadoVerdeViewModel
import com.example.cuidadoverde.viewmodels.ListadodePlantaEspecificoViewModel
import com.squareup.picasso.Picasso


class DetalledePlantasFragment : Fragment() {

    private lateinit var binding: FragmentDetalledePlantasBinding
    private val args: DetalledePlantasFragmentArgs by navArgs()

private lateinit var viewModel: ListadodePlantaEspecificoViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalledePlantasBinding.inflate(inflater, container, false)
        val planta = args.planta


        viewModel= ViewModelProvider(this).get(ListadodePlantaEspecificoViewModel::class.java)

        viewModel.traerplantaespecifica(planta.id)
        viewModel.plantaespecifico_ld.observe(viewLifecycleOwner) {

            binding.idPlanta.text = it?.id.toString()
            binding.nombrePlanta.text = it?.nombre
            binding.Tipo.text = it?.tipo
            binding.imagenURL.setImageResource(0)
            binding.Descripcion.text = it?.descripcion

            //aqui le paso la Url que esta dentro del atributo perro o Dog y tambien la foto
            Picasso.get()
                .load(it?.imagen)
                .into(binding.imagenURL)
        }
        binding.btn1.setOnClickListener{
            sendEmailWithCuidadoVerde(planta?.nombre, planta?.id.toString())

        }


        return binding.root

    }

    fun sendEmailWithCuidadoVerde(nombrePlanta: String?, idPlanta: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("abebello@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiero comprar un futbolista")
        intent.putExtra(
            Intent.EXTRA_TEXT, "Hola\n" +
                    "Vi la planta ${nombrePlanta} de código ${idPlanta} y me gustaría que me contactaran a este correo o al\n" +
                    "siguiente número ___\n" +
                    "Quedo atento."
        )
        val packageManager = requireContext().packageManager

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar por correo"))
        } else {
            Toast.makeText(
                requireContext(),
                "Tienes que tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}


