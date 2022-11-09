package com.aemm.primerproyecto.ejerciciofinal2

/**
 * Interfaz que nos va ayudar para saber cuando el usuario de click
 * a un elemento de la lista
 */
interface RecyclerAnimalListener {
    fun onItemSelected(animal: Animal)
}