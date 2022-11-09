package com.aemm.primerproyecto.ejerciciofinal2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aemm.primerproyecto.R
import java.lang.reflect.Field

/**
 * Activity que contiene el fragment para mostrar un listado de animales.
 */
class AnimalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)

        // Cargamos el Fragment en el contenedor
        this.supportFragmentManager.beginTransaction()
            .add(R.id.fl_container_animal, ListaAnimalesFragment.newInstance(this.getData()), "ListaAnimalesFragment")
            .commit()
    }

    /**
     * Retorna un array de datos del tipo Animal
     */
    private fun getData(): ArrayList<Animal>{
        val data = arrayListOf<Animal>()
        data.add(Animal("León", getResourceIdByNombre("leon", R.drawable::class.java),false, "Masculino"))
        data.add(Animal("Ratón", getResourceIdByNombre("raton", R.drawable::class.java),true, "Masculino"))
        data.add(Animal("Perro", getResourceIdByNombre("perro", R.drawable::class.java),false, "Femenino"))
        data.add(Animal("Gato", getResourceIdByNombre("gato", R.drawable::class.java),true, "Masculino"))
        data.add(Animal("Puma", getResourceIdByNombre("puma", R.drawable::class.java),false, "Femenino"))
        data.add(Animal("Aguila", getResourceIdByNombre("aguila", R.drawable::class.java),true, "Masculino"))
        data.add(Animal("Elefante", getResourceIdByNombre("elefante", R.drawable::class.java),false, "Masculino"))
        data.add(Animal("Gorila", getResourceIdByNombre("gorila", R.drawable::class.java),true, "Masculino"))
        data.add(Animal("Delfín", getResourceIdByNombre("delfin", R.drawable::class.java),false, "Femenino"))

        return data
    }

    companion object {
        /**
         * Método static que obtiene el id de algún recurso(imagen, icon, font, etc).
         */
        fun getResourceIdByNombre(nombreAsset: String, c: Class<*>): Int {
            return try {
                val idComponente: Field = c.getDeclaredField(nombreAsset)
                idComponente.getInt(idComponente)
            } catch (e: Exception) {
                e.printStackTrace()
                -1
            }
        }
    }
}