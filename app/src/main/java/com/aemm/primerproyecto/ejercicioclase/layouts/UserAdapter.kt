package com.aemm.primerproyecto.ejercicioclase.layouts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

/**
 * En el constructor de la clase le pasamos una colección de elementos para renderizar el Layout.
 * Se debe declarar el listener como private
 */
class UserAdapter(private val items: ArrayList<UserItem>, private val listener: RecyclerItemListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       val name: TextView
       val image: ImageView

       init {
           name = view.findViewById(R.id.tvName)
           image = view.findViewById(R.id.ivLogo)
       }
    }

    // Llenar nuestro layout con el ViewHolder, para llenar el Layout padre
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_user, parent, false)

        return UserViewHolder(view)
    }

    // Este método entra cada vez que se recicla o se hace scroll
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.name.text = items[position].name

       // Se agrega un listener para cuando el usuario de click en un elemento
       holder.itemView.setOnClickListener {
           listener.onItemSelected(items[position])
       }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}