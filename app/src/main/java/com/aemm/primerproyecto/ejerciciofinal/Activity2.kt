package com.aemm.primerproyecto.ejerciciofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.aemm.primerproyecto.R

/**
 * Activity2 que lee la información que se le envía Activity1
 */
class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val tvName = this.findViewById<TextView>(R.id.tvNombre)
        val tvApePaterno = this.findViewById<TextView>(R.id.tvApellidoPaterno)
        val tvApeMaterno = this.findViewById<TextView>(R.id.tvApellidoMaterno)
        val tvEdad = this.findViewById<TextView>(R.id.tvEdad)

        // Se lee la información que se envío del Activity1
        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_USUARIO")) {
                val usuario: Usuario = bundle.getSerializable("KEY_USUARIO") as Usuario
                tvName.text = "Nombre: ".plus(usuario.nombre)
                tvApePaterno.text = "Apellido Paterno: ".plus(usuario.apellidoPaterno)
                tvApeMaterno.text = "Apellido Materno: ".plus(usuario.apellidoMaterno)
                tvEdad.text = "Edad: ".plus(usuario.edad.toString())
            }
        } ?: showEmptyIfo()
    }

    private fun showEmptyIfo() {
        Toast.makeText(this, "Extras vacio", Toast.LENGTH_LONG).show()
    }
}