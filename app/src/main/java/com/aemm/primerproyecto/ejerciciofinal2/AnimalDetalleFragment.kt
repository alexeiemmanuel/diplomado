package com.aemm.primerproyecto.ejerciciofinal2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

/**
 * Constante para obtener el argumento de un Animal
 */
private const val ARG_ANIMAL = "ANIMAL"

/**
 * Fragment que va a desplegar información de un Animal.
 */
class AnimalDetalleFragment : Fragment() {

    private lateinit var animal: Animal
    private lateinit var recyclerView: RecyclerView

    /**
     * Se crea el Fragment y se inicializa con los parámetros necesarios
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtenemos los parámetros del constructor static
        arguments?.let {
            this.animal = it.getParcelable(ARG_ANIMAL)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_detalle, container, false)

        val tvNombre = view.findViewById<TextView>(R.id.tvNombreAnimal)
        val ivImagen = view.findViewById<ImageView>(R.id.ivLogoAnimal)
        val cbEnfermedad = view.findViewById<CheckBox>(R.id.cbEnfermedad)
        val rgSexoAnimal = view.findViewById<RadioGroup>(R.id.rgSexoAnimal)
        val btnRegresar = view.findViewById<Button>(R.id.btSend)

        tvNombre.text = animal.nombre
        ivImagen.setImageResource(animal.imagen)
        cbEnfermedad.isChecked = animal.isEnfermo

        if(animal.sexo.equals("Masculino"))
            rgSexoAnimal.check(R.id.rbMas);
        else
            rgSexoAnimal.check(R.id.rbFem);

        // Si el usuario da click lo lleva al Activity principal
        btnRegresar.setOnClickListener {
            val intent = Intent (this@AnimalDetalleFragment.context, AnimalActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Factory para crear una nueva instancia de este fragmento
         * utilizando los parámetros proporcionados.
         *
         * @param animal Instancia del tipo Animal.
         * @return Una instancia de AnimalDetalleFragment.
         */
        @JvmStatic
        fun newInstance(animal: Animal?): AnimalDetalleFragment {
            return AnimalDetalleFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ANIMAL, animal)
                }
            }
        }
    }
}