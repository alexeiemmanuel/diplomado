package com.aemm.primerproyecto.ejercicioclase.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aemm.primerproyecto.R

class FragmentManagerActivity : AppCompatActivity() {

    val name = "Alexei Martínez"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_manager)

        //val fragment =  supportFragmentManager.findFragmentById(R.id.FirstFragment)

        // Cargamos el Fragment en el contenedor
        this.supportFragmentManager.beginTransaction()
            .add(R.id.container, FirstFragment.newInstance(name), "FirstFragment")
            .commit()

    }
}