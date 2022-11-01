package com.aemm.primerproyecto.ejercicioclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.aemm.primerproyecto.R

class ExplicitDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_detail)

        val tvName = this.findViewById<TextView>(R.id.tvName)
        val tvLastName = this.findViewById<TextView>(R.id.tvLastName)
        val tvAge = this.findViewById<TextView>(R.id.tvAge)

        // Se lee la información del ExplicitActivity
        intent.extras?.let { bundle ->
            if (bundle.containsKey("KEY_NAME")) {
                val name = bundle.getString("KEY_NAME")
                tvName.text = "Nombre: ".plus(name)
            }

            if (bundle.containsKey("KEY_LASTNAME")) {
                val lastName = bundle.getString("KEY_LASTNAME")
                tvLastName.text = "Apellido: ".plus(lastName)
            }

            if (bundle.containsKey("KEY_AGE")) {
                val age = bundle.getInt("KEY_AGE")
                tvAge.text = "Edad: ".plus(age.toString())
            }

            if (bundle.containsKey("KEY_USER")) {
                val usuario: Usuario = bundle.getSerializable("KEY_USER") as Usuario
                tvName.text = "Nombre: ".plus(usuario.name)
                tvLastName.text = "Apellido: ".plus(usuario.lastName)
                tvAge.text = "Edad: ".plus(usuario.age.toString())
            }

            if (bundle.containsKey("KEY_ET_NAME")) {
                val etNameString = bundle.getString("KEY_ET_NAME")
                Toast.makeText(this, etNameString, Toast.LENGTH_LONG).show()
            }

        } ?: showEmptyIfo()
    }

    private fun showEmptyIfo() {
        Toast.makeText(this, "Extras vacio", Toast.LENGTH_LONG).show()
    }
}