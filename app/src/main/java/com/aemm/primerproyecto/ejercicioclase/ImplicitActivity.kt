package com.aemm.primerproyecto.ejercicioclase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aemm.primerproyecto.R

class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        val button = this.findViewById<Button>(R.id.btn_enviar)
        button.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayListOf("alexeiemmanuel@aragon.unam.mx"))
                putExtra(Intent.EXTRA_SUBJECT, "Aviso Urgente")
                putExtra(Intent.EXTRA_TEXT, "Ejemplo de body en correo")
            }

            //this.startActivity(email)
            this.startActivity(Intent.createChooser(email, "Test"))
        }


    }
}