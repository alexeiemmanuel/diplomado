package com.aemm.primerproyecto.ejercicioclase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

/**
 * Se implementa la interfaz para el evento Listener
 */
class RecyclerViewActivity : AppCompatActivity(), RecyclerItemListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val list = this.findViewById<RecyclerView>(R.id.list)

        // Le pasamos la colección y ahora el Listener con this
        val userAdapter = UserAdapter(this.getData(), this)

        // Se puede configurar en modo HORIZONTAL
        //list.layoutManager = GridLayoutManager(this, 2)
        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()
        list.adapter = userAdapter

    }

    private fun getData(): ArrayList<UserItem>{
        val data = arrayListOf<UserItem>()
        data.add(UserItem("Juan", ""))
        data.add(UserItem("Jose", ""))
        data.add(UserItem("Pedro", ""))
        data.add(UserItem("Tito", ""))
        data.add(UserItem("Luis", ""))
        data.add(UserItem("Sara", ""))
        data.add(UserItem("Transito", ""))

        return data
    }

    override fun onItemSelected(user: UserItem) {
        Toast.makeText(this, "User: ${user.name}", Toast.LENGTH_SHORT).show()
    }
}