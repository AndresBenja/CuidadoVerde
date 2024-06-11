package com.example.cuidadoverde.repository

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.cuidadoverde.api.retrofit.RetrofitHelper
import com.example.cuidadoverde.api.service.PlantaInterfasService
import com.example.cuidadoverde.models.PlantaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImplementPlanta: InterfasPlanta {

    private val plantaServicio= RetrofitHelper.getRetrofit().create(PlantaInterfasService::class.java)

    override suspend fun traertodaslasplantas(): MutableList<PlantaResponse> {
        // Esta es una corrutina
        return withContext(Dispatchers.IO) {
            // Esta es la corutina que va siempre
          val plantaList= plantaServicio.traertodaslasplantas()
            plantaList
        }
    }

    override suspend fun traerplantaespecifica(plantaid: Int): PlantaResponse {
        return withContext(Dispatchers.IO) {
            plantaServicio.traerplantaespecifica(plantaid)
        }
    }

    fun sendEmailWithCuidadoVerde(context: Context, nombrePlanta: String?, idplanta: String?) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("abebello@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Quiero comprar esta planta")
            putExtra(
                Intent.EXTRA_TEXT, "Hola\n" +
                        "Vi esta planta $nombrePlanta de código $idplanta y me gustaría que me contactaran a este correo o al\n" +
                        "siguiente número ___\n" +
                        "Quedo atento."
            )
        }

        val packageManager = context.packageManager
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(context, Intent.createChooser(intent, "Enviar por correo"), null)
        } else {
            Toast.makeText(
                context,
                "Tienes que tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
