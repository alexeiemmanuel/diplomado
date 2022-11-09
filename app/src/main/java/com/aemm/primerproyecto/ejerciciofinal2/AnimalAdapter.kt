package com.aemm.primerproyecto.ejerciciofinal2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

class AnimalAdapter(
    private val items: ArrayList<Animal>,
    private val fragmentManager: FragmentManager,
    private val listener: RecyclerAnimalListener
) : RecyclerView.Adapter<AnimalViewHolder>() {

    /**
     * Este método es llamado por el RecyclerView cuando requiere crear un ViewHolder para un ítem.
     * Se llena el layout del ítem.
     *
     * @param parent El ViewGroup al que se agregará la nueva vista después de que se vincule a una posición de adaptador.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recyclerview_item_animal, parent, false)

        return AnimalViewHolder(view)
    }

    /**
     * Método para mostrar los datos en la posición especificada.
     *
     * @param holder   ViewHolder que debe ser actualizado para representar el contenido en la posición dada en el conjunto de datos.
     * @param position Posición del elemento a consultar.
     */
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        // Obtenemos una instancia de animal de la colección para llenar el holder con los datos
        val animal: Animal = items[position]
        holder.nombre.text = animal.nombre
        holder.logo.setImageResource(animal.imagen)

        // Se agrega un listener para cuando el usuario de click en un elemento
        holder.itemView.setOnClickListener {
            listener.onItemSelected(items[position])
            this@AnimalAdapter.fragmentManager.beginTransaction()
                .replace(R.id.fl_container_animal, AnimalDetalleFragment.newInstance(items[position]))
                .addToBackStack("AnimalDetalleFragment")
                .commit()
        }
    }

    /**
     * Regresa el número total de elementos del conjunto de datos que tiene el adaptador.
     *
     * @return Retorna el número total de elementos de este adaptador.
     */
    override fun getItemCount(): Int {
        return this.items.size
    }


}