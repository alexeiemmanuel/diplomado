package com.aemm.primerproyecto.ejercicioclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.aemm.primerproyecto.R

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    /**
     * desde Bundle puedo obtener información
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(this.TAG, "Estoy en onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(this.TAG, "Estoy en onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(this.TAG, "Estoy en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(this.TAG, "Estoy en onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(this.TAG, "Estoy en onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.e(this.TAG, "Estoy en onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(this.TAG, "Estoy en onDestroy")
    }
}