package es.grupo04.modelo

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class Guardia(
    @SerializedName("id")
    var id: Int,
    @SerializedName("prof_falta")
    var prof_falta:Int,
    @SerializedName("prof_hace_guardia")
    val prof_hace_guardia: Int,
    @SerializedName("estado")
    var Estado: EnumEstado,
    @SerializedName("fecha")
    var fecha: LocalDate,
    @SerializedName("dia_semana")
    var dia_semana:Int,
    @SerializedName("horario")
    var horario: Horario,
    @SerializedName("hora")
    var hora: String,
    @SerializedName("aviso")
    var aviso: Int,
    @SerializedName("grupo")
    var grupo:String,
    @SerializedName("aula")
    var aula: String,
    @SerializedName("observaciones")
    val observaciones: String,



    ): Serializable