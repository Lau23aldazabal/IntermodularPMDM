package es.grupo04.data.api

import es.grupo04.modelo.*
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.time.LocalDate


private const val URL_BASE =
    "http://192.168.1.131:8080/api/"
private val retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface GuardiasApiService {
    @POST("profesores/login/{user}")
    suspend fun login(
        @Query("user") usuario: String,
        @Query("pwd") contrasenia: String
    ): Profesor

    //métodos de avisos
    @GET("/avisos")
    suspend fun getAvisos(): List<Aviso_Guardia>

    @GET("/avisos/{id}")
    suspend fun getAviso(@Path("id") id: Int): Aviso_Guardia

    @POST("/avisos")
    suspend fun crearAviso(
        @Body()aviso: Aviso_Guardia
    ): Aviso_Guardia?

    @PUT("/avisos/{id}")
    fun actualizarAviso(@Body aviso: Aviso_Guardia, @Path("id") id: Int): Call<Aviso_Guardia>

    @DELETE("/avisos/{id}")
    suspend fun borrarAviso(@Path("id") id: Int): Call<Void>

    //métodos de profesor
    @GET("/profesores")
    suspend fun getProfesores(): List<Profesor>


    @GET("profesores/buscar{id}")
    suspend fun obtenerUno(@Path("id") id: Int): Profesor

    //métodos de guardias
    @GET("/guardias/listar")
    suspend fun getGuardias(): List<Guardia>

    //lista de guardias de un día
    @GET("/listarPorDia{dia}")
    suspend fun getGuardiasDia(@Path("dia") dia: LocalDate): List<Guardia>

    @GET("/guardias/buscar/{id}")
    suspend fun getGuardia(@Path("id") id: Int): Guardia

    @GET("/guardias/listarposiblesguardias")
    suspend fun guardiasPendientes(): List<Guardia>

    @POST("/guardias/crear")
    suspend fun crearGuardia(@Body guardia: Guardia): Call<Guardia>

    @GET("/getAllGuardias/{profesor1_.id}")
    suspend fun guardiasProfesor(@Path("profesor1_.id")id:Int):List<Guardia>

    @PUT("guardias/setEstado/{id}")
    fun cambiarEstadoGuardia(@Path("id")id:Int,@Body estado: String):Guardia

    @DELETE("/guardias/borrar/{id}")
    fun borrarGuardia(@Path("id") id: Int): Call<Void>

    //métodos de horario_guardias
    @GET("/guardias/{profesor}")
    suspend fun getHorarioGuardia(@Path("profesor") id: Int): HorarioGuardias

    //método para obtener el horario de un profesor
    @GET("/horarios/{profesor}")
    suspend fun getHorarioProfesor(
        @Path(value = "profesor")id: Int,
        //@Body()dia: Int
        @Query("dia")dia: Int
    ): List<Horario>


}


object GuardiasApi {
    val retrofitService: GuardiasApiService by lazy { retrofit.create(GuardiasApiService::class.java) }
}