package com.aemm.primerproyecto.ejercicioclase.layouts

/**
 * Interfaz que nos va ayudar para saber cuando el usuario de clic
 * a un elemento de la lista
 */
interface RecyclerItemListener {
    fun onItemSelected(user: UserItem)
}