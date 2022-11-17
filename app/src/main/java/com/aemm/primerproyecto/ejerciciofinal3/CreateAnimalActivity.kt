package com.aemm.primerproyecto.ejerciciofinal3

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aemm.primerproyecto.R
import com.bumptech.glide.Glide

/**
 * Activity que se encarga de insertar los datos de un animal en la Base de Datos
 */
class CreateAnimalActivity : AppCompatActivity() {

    private lateinit var sqlHelper: SqlAnimalHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_animal)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        this.sqlHelper = SqlAnimalHelper(this)

        val btnGuardar = this.findViewById<Button>(R.id.btn_form_guardar)
        val chEnfermo = this.findViewById<CheckBox>(R.id.cb_form_enfermo)
        val spSexo = this.findViewById<Spinner>(R.id.sp_form_sexo)
        val etDescripcion = this.findViewById<EditText>(R.id.et_form_descripcion)
        val etNombre = this.findViewById<EditText>(R.id.et_form_nombre)
        val etUrl = this.findViewById<EditText>(R.id.et_form_url_imagen)
        val ivLogo = this.findViewById<ImageView>(R.id.iv_form_logo)
        var isFormValid: Boolean = true

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text
            val descripcion = etDescripcion.text
            val url = etUrl.text
            val isenfermo = chEnfermo.isChecked
            val sexo = spSexo.selectedItem

            if (nombre.isEmpty()) {
                etNombre.error = "El nombre no puede estar vacío"
                isFormValid = false
            }

            if (descripcion.isEmpty()) {
                etDescripcion.error = "La descripción no puede estar vacía"
                isFormValid = false
            }

            if (url.isEmpty()) {
                etUrl.error = "La URL de la imagen no puede estar vacía"
                isFormValid = false
            }

            if (isFormValid) {
                val animal = Animal(
                    nombre.toString(),
                    descripcion.toString(),
                    url.toString(),
                    isenfermo,
                    sexo.toString()
                )
                val result = sqlHelper.insert(animal)

                if (result > -1) {
                    Toast.makeText(this, "Información Guardada", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Información No Guardada", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Agregamos la imagen cuando se pierde el foco al introducir la URL de la imagen
        etUrl.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                Glide.with(this)
                    .load(etUrl.text.toString())
                    .centerCrop()
                    .into(ivLogo)
            }
        }

    }
}