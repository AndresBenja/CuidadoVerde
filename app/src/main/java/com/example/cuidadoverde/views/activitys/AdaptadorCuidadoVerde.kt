package com.example.cuidadoverde.views.activitys


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuidadoverde.databinding.ItemdetalledeplantaBinding
import com.example.cuidadoverde.models.PlantaResponse
import com.example.cuidadoverde.repository.InterfasPlanta
import com.squareup.picasso.Picasso

//aqui se crea una lista o colecciones
class AdaptadorCuidadoVerde:RecyclerView.Adapter <AdaptadorCuidadoVerde.ViewHolder>() {
    //esta es una funcion que no devuelve nada va a detectar el click
    lateinit var onItemClickListener:(PlantaResponse) ->Unit

    //aqui se declara
    var plantalist= mutableListOf<PlantaResponse>()
        //aqui se llena la lista
        set(value) {
            field=value
            this.notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCuidadoVerde.ViewHolder {
        //aqui se crea el binding para poder pintar la fila conexion del xml con el code
        val binding=ItemdetalledeplantaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AdaptadorCuidadoVerde.ViewHolder, position: Int) {
        //Aqui envio la posicion del perro u objeto del listado
        val planta:PlantaResponse=plantalist[position]
        holder.binplanta(planta)
    }

    override fun getItemCount(): Int {
        //aqui se devuelve el tamaño de la lista
        return plantalist.size
    }
    //un adaptador siempre va acompañado de un viewholder
    //se declara una clase y se crea dentro un viewHolder y le pasamos el bindig
    inner class ViewHolder(private val binding: ItemdetalledeplantaBinding):RecyclerView.ViewHolder(binding.root){
        //aqui se hace referncia al XML para pintar
        fun binplanta(planta: PlantaResponse) {
            //aqui se hace la referencia al binding de el xml
            binding.idPlanta.text=planta.id.toString()
            binding.nombrePlanta.text= planta.nombre
            binding.tipo.text= planta.tipo
            binding.descripcion.text=planta.descripcion
            //se limpia el fondo por defecto
            binding.imagenURL.setImageResource(0)

            //aqui le paso la Url que esta dentro del atributo perro o Dog y tambien la foto
            Picasso.get()
                .load(planta.imagen)
                .into(binding.imagenURL)
            //quiero que toda la fila sea clickeable
            binding.root.setOnClickListener{
                //si la funcion esta inicializada
                if (::onItemClickListener.isInitialized)
                    onItemClickListener(planta)

            }


        }
    }

}