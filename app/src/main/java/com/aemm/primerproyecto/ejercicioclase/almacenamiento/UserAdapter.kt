package com.aemm.primerproyecto.ejercicioclase.almacenamiento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

class UserAdapter(private val users: ArrayList<UserSqlModel>) : RecyclerView.Adapter<UserAdapter.UserSqlViewHolder>() {
    class UserSqlViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idUser : TextView
        val name : TextView
        val description :TextView

        init {
            idUser = view.findViewById(R.id.id_db_user)
            name = view.findViewById(R.id.id_db_nombre)
            description = view.findViewById(R.id.id_db_description)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSqlViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.db_userlist_item, parent, false)
        return UserSqlViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserSqlViewHolder, position: Int) {
        holder.idUser.text = users[position].id.toString()
        holder.name.text = users[position].name
        holder.description.text = users[position].description

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateItems(newItems :ArrayList<UserSqlModel>){
        users.clear()
        users.addAll(newItems)
        notifyDataSetChanged()
    }


}