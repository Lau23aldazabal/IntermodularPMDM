package es.grupo04.modelo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Profesor (
    @SerializedName("id")
    var id: Int,
    @SerializedName("dni")
    val dni:String,
    @SerializedName("user")
    var usuario: String,
    @SerializedName("password")
    var contrasenia: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("ape1")
    val ape1: String,
    @SerializedName("ape2")
    var ape2: String,
    @SerializedName("tfno")
    val tfno:String,
    @SerializedName("baja")
    val baja: Int,
    @SerializedName("activo")
    val activo: Int,
    @SerializedName("dept_cod")
    var dept_cod: String?,
    @SerializedName("id_sustituye")
    var id_sustituye: Int,
        ): Serializable