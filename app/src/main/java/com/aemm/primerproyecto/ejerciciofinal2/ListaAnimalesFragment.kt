package com.aemm.primerproyecto.ejerciciofinal2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

/**
 * Constante para obtener el argumento de una colección
 */
private const val ARG_COLLECTION = "COLECCION"


/**
 * Fragment que va a desplegar una lista de animales.
 */
class ListaAnimalesFragment : Fragment(), RecyclerAnimalListener {

    private var lista: ArrayList<Animal>  = ArrayList()
    private var itemSelected: Animal? = null
    private lateinit var recyclerView: RecyclerView

    /**
     * Se crea el Fragment y se inicializa con los parámetros necesarios
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtenemos los parámetros del constructor static
        arguments?.let {
            this.lista = it.getParcelableArrayList(ARG_COLLECTION)!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_lista_animales, container, false)
        this.recyclerView = view.findViewById(R.id.recycler_view_animales);
        this.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        this.recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        this.recyclerView.itemAnimator = DefaultItemAnimator()
        this.recyclerView.adapter = AnimalAdapter(this.lista, this.parentFragmentManager ,this)

        return view
    }


    companion object {
        /**
         * Factory para crear una nueva instancia de este fragmento
         * utilizando los parámetros proporcionados.
         *
         * @param lista Array del tipo Animal.
         * @return Una instancia de ListaAnimalesFragment.
         */
        @JvmStatic
        fun newInstance(lista: ArrayList<Animal>): ListaAnimalesFragment {
            return ListaAnimalesFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_COLLECTION, lista)
                }
            }
        }
    }

    override fun onItemSelected(animal: Animal) {
        this.itemSelected = animal
    }
}