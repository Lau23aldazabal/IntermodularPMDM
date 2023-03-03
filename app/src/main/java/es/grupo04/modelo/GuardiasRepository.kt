package es.grupo04.modelo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.grupo04.data.api.GuardiasApi
import es.grupo04.data.api.GuardiasApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.security.Guard
import java.time.LocalDate

class GuardiasRepository {
    //login
    //suspend fun login(user: String, pwd: String): Call<Profesor> =
      //  GuardiasApi.retrofitService.login(user, pwd)
    suspend fun login(usuario: String, contrasena: String): Profesor? {
        return GuardiasApi.retrofitService.login(usuario, contrasena)
    }
    //métodos guardias
    suspend fun crearGuardia(nueva: Guardia): Call<Guardia> {
        return GuardiasApi.retrofitService.crearGuardia(nueva)
    }
    suspend fun getGuardias(): List<Guardia> = GuardiasApi.retrofitService.getGuardias()
    suspend fun getGuardia(id: Int): Guardia = GuardiasApi.retrofitService.getGuardia(id)
    suspend fun guardiasPendientes(): List<Guardia> = GuardiasApi.retrofitService.guardiasPendientes()
    suspend fun guardiasProfesor(id:Int): List<Guardia> = GuardiasApi.retrofitService.guardiasProfesor(id)
    suspend fun cambiarEstadoGuardia(id:Int, estado:String):Guardia=GuardiasApi.retrofitService.cambiarEstadoGuardia(id,estado)
    suspend fun borrarGuardia(id:Int):Call<Void>{
        return GuardiasApi.retrofitService.borrarGuardia(id)
    }
    suspend fun getGuardiasDia(dia: LocalDate) = GuardiasApi.retrofitService.getGuardiasDia(dia)
    //métodos avisos
    suspend fun getAvisos(): List<Aviso_Guardia> = GuardiasApi.retrofitService.getAvisos()
    suspend fun getAviso(id: Int): Aviso_Guardia = GuardiasApi.retrofitService.getAviso(id)
    suspend fun crearAviso(avisoGuardia: Aviso_Guardia) = GuardiasApi.retrofitService.crearAviso(avisoGuardia)
    suspend fun actualizarAviso(nuevo: Aviso_Guardia,id: Int):Call<Aviso_Guardia>{
        return GuardiasApi.retrofitService.actualizarAviso(nuevo,id)
    }
    suspend fun borrarAviso(id:Int):Call<Void>{
        return GuardiasApi.retrofitService.borrarAviso(id)
    }











//métodos profesores

    suspend fun getProfesores(): List<Profesor> = GuardiasApi.retrofitService.getProfesores()
    suspend fun getHorarioProfesor(id: Int,dia:Int) = GuardiasApi.retrofitService.getHorarioProfesor(id,dia)
    suspend fun obtenerUno(id: Int): Profesor {
       return GuardiasApi.retrofitService.obtenerUno(id)

    }
    //método horario guardias
    suspend fun getHorarioGuardia(profesor: Int): HorarioGuardias =
        GuardiasApi.retrofitService.getHorarioGuardia(profesor)


}