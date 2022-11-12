package com.aemm.primerproyecto.ejercicioclase.almacenamiento

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aemm.primerproyecto.R

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)

        val btSave = this.findViewById<Button>(R.id.btn_save_file)
        val etInfo = this.findViewById<EditText>(R.id.etInfo)

        val fileName = "test.txt"
        val body = "body example"

        this.resources.openRawResource(R.raw.ejemplo_raw).use{ stream ->
            val text = stream.bufferedReader().use{
                it.readText()
            }
            Toast.makeText(this, "Guardado en raw: $text", Toast.LENGTH_LONG).show()
        }

        btSave.setOnClickListener {
            this.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
              //output.write(body.toByteArray())
              output.write(etInfo.text.toString().toByteArray())
            }

            this.openFileInput(fileName).use { stream ->

                val text = stream.bufferedReader().use{
                    it.readText()
                }

                Toast.makeText(this, "Guardado: ${text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}