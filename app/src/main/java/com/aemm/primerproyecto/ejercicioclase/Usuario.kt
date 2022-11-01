package com.aemm.primerproyecto.ejercicioclase

import java.io.Serializable

data class Usuario (
    val name: String,
    val lastName: String,
    val age: Int
): Serializable