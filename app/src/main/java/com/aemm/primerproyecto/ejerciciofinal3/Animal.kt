package com.aemm.primerproyecto.ejerciciofinal3

import java.io.Serializable

/**
 * Clase que encapsula datos de un animal
 */
data class Animal(
    val nombre: String,
    val descripcion: String,
    val imagen: String,
    val isEnfermo: Boolean,
    val sexo: String,
    val id: Int = 0
): Serializable