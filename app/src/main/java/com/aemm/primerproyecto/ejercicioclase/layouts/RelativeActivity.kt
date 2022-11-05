package com.aemm.primerproyecto.ejercicioclase.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import com.aemm.primerproyecto.R

class RelativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)

        val cbCredit = this.findViewById<CheckBox>(R.id.cbCredit)
        val btSave = this.findViewById<Button>(R.id.btSend)
        val rgSex = this.findViewById<RadioGroup>(R.id.rgSex)

        cbCredit.isChecked = true
        cbCredit.setOnCheckedChangeListener{compoundButton, isChecked ->
            btSave.isEnabled = isChecked != false
            Toast.makeText(this, "isChecked: $isChecked", Toast.LENGTH_LONG).show()
        }

        rgSex.check(R.id.rbFem)
        rgSex.setOnCheckedChangeListener{ _, id ->
            val selectedRb = when(id){
                R.id.rbMas -> "Masculino"
                R.id.rbFem -> "Femenino"
                else -> "Opción no definida"
            }
            Toast.makeText(this, "radioButtonSelected: $selectedRb", Toast.LENGTH_LONG).show()
        }

        // Forma con Adapter sin Lambda
        /*
        rgSex.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(radioGroup: RadioGroup?, id: Int) {
                val selectedRb = when(id){
                    R.id.rbMas -> "Masculino"
                    R.id.rbFem -> "Femenino"
                    else -> "Opción no definida"
                }
                Toast.makeText(this@RelativeActivity, "radioButtonSelected: $selectedRb", Toast.LENGTH_LONG).show()
            }
        })
        */

        val spinner = this.findViewById<Spinner>(R.id.spinner)
        val datos = arrayListOf<String>("Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5", "Elemento 6", "Elemento 7")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, datos)

        // Separa los elementos para que no se vean juntos
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adaptador

        // Esta forma de implementar la interfaz es porque no tiene una implementación lambda
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val itemSelected = parent?.getItemAtPosition(position)
                // val itemSelected = datos[position]

                // Al pasar el contexto le indicamos con this@RelativeActivity, de lo contrario genera error
                Toast.makeText(this@RelativeActivity, "Selected: $itemSelected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }



        btSave.setOnClickListener {
            val isCheck = cbCredit.isChecked
            val itemSelectedPos = spinner.selectedItemPosition
            val spinnerItem = datos[itemSelectedPos]

            val selectedRb = when(rgSex.checkedRadioButtonId){
                R.id.rbMas -> "Masculino"
                R.id.rbFem -> "Femenino"
                else -> "Opción no definida"
            }

            Toast.makeText(this, "isChecked: $isCheck, \n RadioSelected: $selectedRb, \n itemSelected: $spinnerItem", Toast.LENGTH_LONG).show()
        }

    }
}