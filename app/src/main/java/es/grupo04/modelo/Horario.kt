package es.grupo04.modelo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
data class Horario(
    @SerializedName("id")
    var id: Int,
    @SerializedName("profesor")
    val profesor:Int,
    @SerializedName("dia_semana")
    val dia_semana: Int,
    @SerializedName("hora")
    val hora: Int,
    @SerializedName("aula")
    var aula: String,
    @SerializedName("grupo")
    val grupo:String,
    @SerializedName("materia")
    val materia: String,
    @SerializedName("genera_guardia")
    var genera_guardia: Int,
       ): Serializable, Parcelable