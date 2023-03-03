package es.grupo04.modelo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.sql.Time
import java.time.LocalDate
import java.util.*

@Parcelize
data class Aviso_Guardia(
    @SerializedName("id_aviso")
    var id_aviso: Int,
    @SerializedName("profesor")
    var profesor: Profesor?,
    @SerializedName("horario")
    val horario: Horario?,
    @SerializedName("motivo")
    var motivo: String,
    @SerializedName("observaciones")
    var observaciones: String,
    @SerializedName("confirmado")
    var confirmado: Boolean,
    @SerializedName("anulado")
    public var anulado: Boolean?,
    @SerializedName("fecha_aviso")
    var fecha_aviso: LocalDate?,
    @SerializedName("hora_aviso")
    var hora_aviso: Time?,

    ): Serializable, Parcelable {
    constructor() : this(0, null,null,"","",false,false,null,null)
}