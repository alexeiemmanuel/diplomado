package com.aemm.primerproyecto.ejerciciofinal3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

/**
 * Activity principal que muestra una lista de Animales de una base de datos de SQLite.
 */
class MainAnimalActivity : AppCompatActivity(), RecyclerAnimalListener {

    /**
     * Propiedad necesaria para realizar la consulta a la Base de Datos.
     */
    private lateinit var sqlHelper: SqlAnimalHelper

    /**
     * Adaptador para llenar la layout del tipo RecyclerView
     */
    private lateinit var animalAdapter: AnimalAdapter

    /**
     * Array del tipo Animal que se obtiene de la base y se le pasara al Adapter
     */
    private var collection: ArrayList<Animal> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_animal)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        this.sqlHelper = SqlAnimalHelper(this)

        val flBtnCrear = this.findViewById<FloatingActionButton>(R.id.fab_add_animal)
        val tvMensaje = this.findViewById<TextView>(R.id.tv_mensaje_animales)
        val list = this.findViewById<RecyclerView>(R.id.rv_list_animales_db)
        val svBuscador = this.findViewById<SearchView>(R.id.search_animales)

        this.collection = sqlHelper.getAllAnimales()
        this.animalAdapter = AnimalAdapter(this.collection, this)

        if(this.collection.size <= 0){
            tvMensaje.visibility = TextView.VISIBLE
        }

        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()
        list.adapter = this.animalAdapter

        // Se invoca al la Activity para crear animales
        flBtnCrear.setOnClickListener {
            val intentExplicit = Intent(this, CreateAnimalActivity::class.java)
            this.startActivity(intentExplicit)
        }

        // Evento que dispara cuando el usuario empieza a capturar texto
        svBuscador.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(nombreAnimal: String): Boolean {

                if (nombreAnimal.isEmpty()){
                    tvMensaje.visibility = TextView.INVISIBLE
                    collection = sqlHelper.getAllAnimales()
                    animalAdapter.updateItems(collection)
                }else{
                    this@MainAnimalActivity.buscarAnimales(nombreAnimal, tvMensaje)
                }

                return false
            }
        })
    }

    /**
     * Método que se sobre-escribe para actualizar los items
     */
    override fun onResume() {
        super.onResume()
        this.animalAdapter.updateItems(sqlHelper.getAllAnimales())
    }

    override fun onItemSelected(animal: Animal) {
        Toast.makeText(this, "Animal: ${animal.nombre}", Toast.LENGTH_SHORT).show()
    }

    /**
     * Método que filtra la lista de animales por su nombre
     *
     * @param nombreAnimal Nombre del animal a filtrar
     * @param componente Componente que se va a cambiar su propiedad a visible
     */
    private fun buscarAnimales(nombreAnimal: String, componente: View) {
        val animalesFiltrados: ArrayList<Animal> = ArrayList()

        for (item in this.collection) {
            if (item.nombre.lowercase(Locale.ROOT).contains(nombreAnimal.lowercase(Locale.ROOT))) {
                animalesFiltrados.add(item)
            }
        }

        if (animalesFiltrados.size <= 0) {
            componente.visibility = TextView.VISIBLE
        } else {
            componente.visibility = TextView.INVISIBLE
        }

        this.animalAdapter.updateItems(animalesFiltrados)
    }

}