package com.example.cuidadoverde.views.activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.cuidadoverde.ListadodePlantasFragment
import com.example.cuidadoverde.ListadodePlantasFragmentDirections
import com.example.cuidadoverde.R
import com.example.cuidadoverde.models.PlantaResponse
import com.example.cuidadoverde.repository.ImplementPlanta

class MainActivity : AppCompatActivity(),ListadodePlantasFragment.PlantasSelectListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun plantaSelect(planta:PlantaResponse ) {
        findNavController(R.id.fragmentContainerView).navigate(ListadodePlantasFragmentDirections.actionListadodePlantasFragmentToDetalledePlantasFragment(planta))
    }
}