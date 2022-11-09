package com.aemm.primerproyecto.ejerciciofinal2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

/**
 * Representa el contenido que se va a mostrar
 */
class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nombre: TextView
    val logo: ImageView

    init {
        nombre = view.findViewById(R.id.tvNombreAnimal)
        logo = view.findViewById(R.id.ivLogoAnimal)
    }
}