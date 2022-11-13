package com.aemm.primerproyecto.ejercicioclase.almacenamiento

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aemm.primerproyecto.R

class SqliteActivity : AppCompatActivity() {

    private lateinit var sqlHelper: SqlHelper
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        this.sqlHelper = SqlHelper(this)

        val etName = this.findViewById<EditText>(R.id.et_db_user_name)
        val etDescription = this.findViewById<EditText>(R.id.et_db_user_description)
        val btnAdd = this.findViewById<Button>(R.id.btn_db_guardar)
        val btnList = this.findViewById<Button>(R.id.btn_db_getuser)
        val btnUpdate = this.findViewById<Button>(R.id.btn_db_updateuser)
        val btnDelete = this.findViewById<Button>(R.id.btn_db_deleteuser)

        val list = findViewById<RecyclerView>(R.id.rv_db_list_user)
        userAdapter = UserAdapter(sqlHelper.getAllUsers())

        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        list.itemAnimator = DefaultItemAnimator()

        list.adapter = userAdapter

        btnAdd.setOnClickListener {
            if (etName.text.toString().isEmpty() || etDescription.text.toString().isEmpty()) {
                Toast.makeText(this, "Ingrese Información", Toast.LENGTH_LONG).show()
            } else {
                val user = UserSqlModel(name = etName.text.toString(), description = etDescription.text.toString())
                var result = sqlHelper.insert(user)

                if (result > -1) {
                    Toast.makeText(this, "Información Guardada", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Información No Guardada", Toast.LENGTH_LONG).show()
                }
            }

            userAdapter.updateItems(sqlHelper.getAllUsers())
        }

        btnList.setOnClickListener {
            val list = sqlHelper.getAllUsers()
            Log.e("Lista", list.toString())
        }

        btnUpdate.setOnClickListener {
            val userUpdated = UserSqlModel(id = 1, name = "Jose Estrada", description = "Se actualizo")
            val result = sqlHelper.updateUser(userUpdated)
            if (result > -1) {
                Toast.makeText(this, "Información actualizada", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Información No actualizada", Toast.LENGTH_LONG).show()
            }
        }

        btnDelete.setOnClickListener {
            val result = sqlHelper.deleteUser(1)
            if (result > 0) {
                Toast.makeText(this, "Información eliminada", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Información No eliminada", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        userAdapter.updateItems(sqlHelper.getAllUsers())
    }
}