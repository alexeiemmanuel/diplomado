package com.aemm.primerproyecto.ejercicioclase.fragments.botomNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aemm.primerproyecto.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Activity que muestra un Menu
 */
class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        // Muestra un icono de regreso en la barra de ActionBar
        //this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Cargamos el primer fragment de lo contrario el menu estará vacío
        this.loadFragment(Opcion2Fragment())

        // Switcheamos entre los tres distintos fragments que el usuario va a seleccionar
        val menu = this.findViewById<BottomNavigationView>(R.id.menu)
        menu.selectedItemId = R.id.option_2  // Le indicamos al menu que el icono este seleccionado

        menu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.option_1 ->{
                    this.loadFragment(Opcion1Fragment())

                    return@setOnItemSelectedListener true
                }

                R.id.option_2 ->{
                    this.loadFragment(Opcion2Fragment())

                    return@setOnItemSelectedListener true
                }

                R.id.option_3 ->{
                    this.loadFragment(Opcion3Fragment())

                    return@setOnItemSelectedListener true
                }

                // El listener a fuerzas necesita que regrese un booleano
                // si se da un return true te marca error porque finaliza el codigo
                else -> return@setOnItemSelectedListener false
            }
        }

    }

    // Función que carga un fragment
    private fun loadFragment(fragment: Fragment){
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.root, fragment)
            .commit()
    }
}