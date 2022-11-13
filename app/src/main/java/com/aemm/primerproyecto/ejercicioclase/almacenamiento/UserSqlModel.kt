package com.aemm.primerproyecto.ejercicioclase.almacenamiento

/**
 * Modelo para usar en nuestra base de Datos
 */
data class UserSqlModel(
    val id:Int = 0,
    val name:String = "",
    val description:String = ""
)