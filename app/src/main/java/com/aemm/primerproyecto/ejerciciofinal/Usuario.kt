package com.aemm.primerproyecto.ejerciciofinal

import java.io.Serializable

data class Usuario(
    val nombre:String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val edad: Int
):Serializable