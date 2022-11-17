package com.aemm.primerproyecto.ejerciciofinal3

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R
import com.bumptech.glide.Glide

/**
 * Adapter que necesita el RecyclerView
 */
class AnimalAdapter(
    private val animales: ArrayList<Animal>,
    private val listener: RecyclerAnimalListener) : RecyclerView.Adapter<AnimalAdapter.AnimalSqlViewHolder>() {

    companion object{
        /**
         * Constante para pasar el ID del Animal al Intent paa la Activity DetalleAnimalActivity
         */
        const val KEY_ANIMAL = "KEY_ANIMAL"
    }


    /**
     * ViewHolder para mostrar el nombre y la imagen en el RecyclerView
     */
    class AnimalSqlViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name : TextView
        val foto: ImageView

        init {
            name = view.findViewById(R.id.tv_nombre_animal_db)
            foto = view.findViewById(R.id.iv_imagen_animal_db)
        }
    }

    /**
     * Este método es llamado por el RecyclerView cuando requiere crear un ViewHolder para un ítem.
     * Se llena el layout del ítem.
     *
     * @param parent El ViewGroup al que se agregará la nueva vista después de que se vincule a una posición de adaptador.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalSqlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_animal, parent, false)

        return AnimalSqlViewHolder(view)
    }

    /**
     * Método para mostrar los datos en la posición especificada y actualizarlos con información
     * o modificar su comportamiento.
     *
     * @param holder   ViewHolder que debe ser actualizado para representar el contenido en la posición dada en el conjunto de datos.
     * @param position Posición del elemento a consultar.
     */
    override fun onBindViewHolder(holder: AnimalSqlViewHolder, position: Int) {
        val animal = animales[position]
        holder.name.text = animal.nombre

        Glide.with(holder.foto.context)
            .load(animal.imagen)
            .centerCrop()
            .into(holder.foto)

        holder.itemView.setOnClickListener {
            listener.onItemSelected(animal)

            val intent = Intent(it.context, DetalleAnimalActivity::class.java).apply {
                putExtra(KEY_ANIMAL, animal.id)
            }

            it.context.startActivity(intent)
        }
    }

    /**
     * Regresa el número total de elementos del conjunto de datos que tiene el adaptador.
     *
     * @return Retorna el número total de elementos de este adaptador.
     */
    override fun getItemCount(): Int {
        return animales.size
    }

    /**
     * Método que actualiza el array que contiene los animales y nos servirá para actualizar la vista
     */
    fun updateItems(newItems :ArrayList<Animal>){
        animales.clear()
        animales.addAll(newItems)
        notifyDataSetChanged()
    }


}