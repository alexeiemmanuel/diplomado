package com.aemm.primerproyecto.ejercicioclase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aemm.primerproyecto.R


class FirstFragment : Fragment() {

    var name: String? = null


    // static
    companion object{
        fun newInstance(name: String): FirstFragment{
            return FirstFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_NAME", name)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            name = it.getString("ARG_NAME")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        (activity as FragmentManagerActivity).name

        val view = inflater.inflate(R.layout.fragment_first, container, false)

        // Se cambia la búsqueda del componente Fragment
        val tvNext = view.findViewById<TextView>(R.id.tvNext)
        tvNext.text = name


        tvNext.setOnClickListener {
            // Llamar al FragmentManager
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance(name?: "Alexei"))
                .addToBackStack("SecondFragment")  // Se agrega a la pila y al momento de dar Back
                .commit()
        }

        return view
    }
}