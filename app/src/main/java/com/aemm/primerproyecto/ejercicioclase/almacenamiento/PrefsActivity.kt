package com.aemm.primerproyecto.ejercicioclase.almacenamiento

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.aemm.primerproyecto.R

class PrefsActivity : AppCompatActivity() {

    private lateinit var tvPrefNombre: TextView
    private lateinit var etPrefNombre: EditText
    private lateinit var btnPrefGuardar: Button
    private lateinit var btnPrefEliminar: Button

    private lateinit var prefs: SharedPreferences
    private val PREFS_NAME = "com.aemm.sharepreferences"
    private val SHARE_NAME = "share_name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefs)

        prefs = this.getSharedPreferences(this.PREFS_NAME, Context.MODE_PRIVATE)
        tvPrefNombre = this.findViewById(R.id.tv_pref_name)
        etPrefNombre = this.findViewById(R.id.et_pref_name)
        btnPrefGuardar = this.findViewById(R.id.btn_guardar)
        btnPrefEliminar = this.findViewById(R.id.btn_eliminar)


        // Se guarda en el dispositivo en la ruta /data/data/com.aemm.primerproyecto/com.aemm.sharedpreferences.xml
        btnPrefGuardar.setOnClickListener {
            prefs.edit().putString(this.SHARE_NAME, etPrefNombre.text.toString()).apply()
            configView()

        }

        btnPrefEliminar.setOnClickListener {
            prefs.edit().remove(this.SHARE_NAME).apply()
            configView()
        }

        configView()
    }

    private fun askInfo(){
        tvPrefNombre.visibility = View.INVISIBLE
        btnPrefEliminar.visibility = View.INVISIBLE
        etPrefNombre.visibility = View.VISIBLE
        btnPrefGuardar.visibility = View.VISIBLE
    }

    private fun showInfo(){
        etPrefNombre.visibility = View.INVISIBLE
        btnPrefGuardar.visibility = View.INVISIBLE
        tvPrefNombre.visibility = View.VISIBLE
        btnPrefEliminar.visibility = View.VISIBLE

        tvPrefNombre.text = "Hola ${prefs.getString(this.SHARE_NAME, "")}"
    }

    private fun isInfoSaved():Boolean{
        val myName = prefs.getString(this.SHARE_NAME, "")

        return myName?.isNotEmpty() == true
    }

    private fun configView(){
        if(isInfoSaved()) this.showInfo() else this.askInfo()
    }

}