package com.aemm.primerproyecto.ejerciciofinal2

import android.os.Parcel
import android.os.Parcelable

/**
 * Clase que encapsula datos de un animal y que implementa Parcel para que se pasen en el Intent
 * entre Activity's o Fragment's.
 */
data class Animal(
    val nombre: String,
    val imagen: Int,
    val isEnfermo: Boolean,
    val sexo: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(imagen)
        parcel.writeByte(if (isEnfermo) 1 else 0)
        parcel.writeString(sexo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }
}