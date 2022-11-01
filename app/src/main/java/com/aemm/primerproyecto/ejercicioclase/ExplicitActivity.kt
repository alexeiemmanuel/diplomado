package com.aemm.primerproyecto.ejercicioclase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aemm.primerproyecto.R

class ExplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        val btnSent = this.findViewById<Button>(R.id.btnSend)
        val etName = this.findViewById<EditText>(R.id.etNAme)


        btnSent.setOnClickListener{

            var text = etName.text.toString()

            if(text.isEmpty()){
                Toast.makeText(this, "Introduce un nombre", Toast.LENGTH_SHORT).show()
            }else{
                // Intent Explicit
                val intentExplicit = Intent(this, ExplicitDetailActivity::class.java).apply {
                    putExtra("KEY_NAME", "Alexei Emmanuel")
                    putExtra("KEY_LASTNAME", "Martinez")
                    putExtra("KEY_AGE", 35)
                    val user = Usuario("Macario", "Lopez", 50)
                    putExtra("KEY_USER", user)

                    putExtra("KEY_ET_NAME", text)
                }

                this.startActivity(intentExplicit)
            }

        }
    }
}