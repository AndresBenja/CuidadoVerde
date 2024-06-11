package com.example.cuidadoverde.models

import android.os.Parcel
import android.os.Parcelable

data class PlantaResponse (
    val id: Int,
    val nombre: String?,
    val tipo: String?,
    val imagen: String?,
    val descripcion: String?
)
    : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(tipo)
        parcel.writeString(imagen)
        parcel.writeString(descripcion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlantaResponse> {
        override fun createFromParcel(parcel: Parcel): PlantaResponse {
            return PlantaResponse(parcel)
        }

        override fun newArray(size: Int): Array<PlantaResponse?> {
            return arrayOfNulls(size)
        }
    }

}
