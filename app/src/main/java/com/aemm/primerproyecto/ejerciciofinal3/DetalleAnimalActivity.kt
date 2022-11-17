package com.aemm.primerproyecto.ejerciciofinal3

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
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
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DetalleAnimalActivity : AppCompatActivity() {

    /**
     * Helper que nos permite hacer operaciones a la Base de Datos.
     */
    private lateinit var sqlHelper: SqlAnimalHelper

    /**
     * Id del Animal que se obtiene a través del intent
     */
    private var idAnimal: Int = -1

    /**
     * Entidad del tipo Animal que se obtiene de la base de datos y nos servirá para
     */
    private lateinit var photo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_animal)

        sqlHelper = SqlAnimalHelper(this)

        // Componentes del Formulario
        val etNombre = this.findViewById<EditText>(R.id.et_form_nombre)
        val etUrlImagen = this.findViewById<EditText>(R.id.et_form_url_imagen)
        val etDescription = this.findViewById<EditText>(R.id.et_form_descripcion)
        val ivPhoto = this.findViewById<ImageView>(R.id.iv_form_logo)
        val cbEnfermo = this.findViewById<CheckBox>(R.id.cb_form_enfermo)
        val spSexo = this.findViewById<Spinner>(R.id.sp_form_sexo)
        val opcionesSpinner: Array<String> = this.resources.getStringArray(R.array.form_sexo_array)

        // Botones para hacer las operaciones Eliminar y Actualizar
        val btnUpdate = this.findViewById<Button>(R.id.btn_form_actualizar)
        val btnDelete = this.findViewById<Button>(R.id.btn_form_eliminar)

        // Se lee la información de MainAnimalActivity
        intent.extras?.let { bundle ->
            if (bundle.containsKey(AnimalAdapter.KEY_ANIMAL)) {
                // Consultamos el animal en la base de datos por el id a través del Intent
                this.idAnimal = bundle.getInt(AnimalAdapter.KEY_ANIMAL)
                val animal = this.sqlHelper.getAnimalById(idAnimal)

                // Llenamos el formulario con la entidad Animal
                if (animal != null) {
                    etNombre.setText(animal.nombre)
                    etUrlImagen.setText(animal.imagen)
                    etDescription.setText(animal.descripcion)
                    cbEnfermo.isChecked = animal.isEnfermo
                    spSexo.setSelection(opcionesSpinner.indexOf(animal.sexo))
                    Glide.with(ivPhoto.context)
                        .load(animal.imagen)
                        .centerCrop()
                        .into(ivPhoto)
                }
            }
        }

        // Cargamos la nueva imagen cuando se pierde el foco al introducir la URL de la imagen
        etUrlImagen.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                Glide.with(this)
                    .load(etUrlImagen.text.toString())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(ivPhoto)
            }
        }

        // Variable que servirá para comprobar si el formulario es válido.
        var isFormValid: Boolean = true

        // Se agrega la lógica al hacer click de actualizar la información
        btnUpdate.setOnClickListener {
            val nombre = etNombre.text.trim()
            val descripcion = etDescription.text.trim()
            val url = etUrlImagen.text.trim()
            val isenfermo = cbEnfermo.isChecked
            val sexo = spSexo.selectedItem

            if (nombre.isEmpty()) {
                etNombre.error = "El nombre no puede estar vacío"
                isFormValid = false
            }

            if (descripcion.isEmpty()) {
                etDescription.error = "La descripción no puede estar vacía"
                isFormValid = false
            }

            if (url.isEmpty()) {
                etUrlImagen.error = "La URL de la imagen no puede estar vacía"
                isFormValid = false
            }

            if (isFormValid) {
                val animal = Animal(
                    nombre.toString(),
                    descripcion.toString(),
                    url.toString(),
                    isenfermo,
                    sexo.toString(),
                    this.idAnimal
                )
                val result = sqlHelper.updateAnimal(animal)

                if (result > 0) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Exito")
                    builder.setMessage("La información ha sido actualiza.")
                    builder.setPositiveButton("Aceptar",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                    builder.create()
                    builder.show()
                }
            }
        }

        // Se agrega la lógica al hacer click de eliminar la información
        btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Atención")
            builder.setMessage("¿Estas seguro de eliminar el animal del catalogo?")
            builder.setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                    val result = sqlHelper.deleteAnimal(this@DetalleAnimalActivity.idAnimal)
                    if (result > 0) {
                        val intent = Intent(it.context, MainAnimalActivity::class.java)
                        it.context.startActivity(intent)
                    } else {
                        Toast.makeText(this, "Información No eliminada", Toast.LENGTH_LONG).show()
                    }
                })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Se cierra el dialogo no hay operaciones que hacer
                    })
            builder.create()
            builder.show()
        }
    }

}