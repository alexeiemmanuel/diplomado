package com.aemm.primerproyecto.ejerciciofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aemm.primerproyecto.R
import com.aemm.primerproyecto.ejerciciofinal.Activity2

/**
 * Activity_1 que contiene un formulario para introducir datos del usuario
 */
class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        // Componente Button
        val btnEnviar = this.findViewById<Button>(R.id.btnEnviar)

        // Componentes EditText
        val etNombre = this.findViewById<EditText>(R.id.etNombre)
        val etApellidoPaterno = this.findViewById<EditText>(R.id.etApellidoPaterno)
        val etApellidoMaterno = this.findViewById<EditText>(R.id.etApellidoMaterno)
        val etEdad = this.findViewById<EditText>(R.id.etEdad)

        btnEnviar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val apePaterno = etApellidoPaterno.text.toString()
            val apeMaterno = etApellidoMaterno.text.toString()
            val edad = etEdad.text.toString()

            if(nombre.isEmpty() || apePaterno.isEmpty() || apeMaterno.isEmpty() || edad.isEmpty()) {
                Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show()
            } else {
                // Se crea un Intent Explicit
                val intentExplicit = Intent(this, Activity2::class.java).apply {
                    val usuario = Usuario(nombre, apePaterno, apeMaterno, edad.toInt())
                    putExtra("KEY_USUARIO", usuario)
                }

                this.startActivity(intentExplicit)
            }

        }
    }
}