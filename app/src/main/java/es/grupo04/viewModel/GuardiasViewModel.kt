package es.grupo04.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.grupo04.data.api.GuardiasApi
import es.grupo04.modelo.*
import kotlinx.coroutines.launch
import retrofit2.await
import java.security.MessageDigest
import java.time.LocalDate
import java.util.*


class GuardiasViewModel() : ViewModel() {
    //listas
    val listaGuardias = MutableLiveData<List<Guardia>>()
    val listaHorariosLiveData = MutableLiveData<List<Horario>>()
    val listaAvisos = MutableLiveData<List<Aviso_Guardia>>()
    val listaProfesores = MutableLiveData<List<Profesor>>()
    val fecha_aviso = MutableLiveData<LocalDate>()
    val guardiasPendientes = MutableLiveData<List<Guardia>>()
    val guardiasProfesor = MutableLiveData<List<Guardia>>()
    val listaHorasConfirmadas = MutableLiveData<List<Horario>>()
    val lista: MutableList<Horario> = mutableListOf()


    //objetos
    val profesorLiveData = MutableLiveData<Profesor?>()
    val avisoLiveData = MutableLiveData<Aviso_Guardia>();
    val guardia = MutableLiveData<Guardia>()
    val horarioGuardias = MutableLiveData<HorarioGuardias>()
    val horario = MutableLiveData<Horario>()
    val repository = GuardiasRepository();
    private val _guardiaCreada = MutableLiveData<Guardia>()
    val guardiaCreada: LiveData<Guardia> = _guardiaCreada

    //login
    suspend fun cargarProfesor(user: String, password: String) = repository.login(user, password)

    fun login(usuario: String, contrasena: String) {
        val md = MessageDigest.getInstance("MD5")
        val contrasenaEncriptada = md.digest(contrasena.toByteArray()).joinToString("") {
            "%02x".format(it)
        }
        viewModelScope.launch {
            val result = repository.login(usuario, contrasenaEncriptada)
            profesorLiveData.postValue(result)
        }
    }

    //métodos de guardias
    fun fechaAviso(param: LocalDate) {
        fecha_aviso.value = param
    }

    fun aniadirhorario(horario: Horario) {
        lista.add(horario)
        listaHorasConfirmadas.value = lista
    }

    fun quitarhorario(horario: Horario) {
        lista.remove(horario)
        listaHorasConfirmadas.value = lista
    }

    fun guardiasPendientes() {
        viewModelScope.launch {
            guardiasPendientes.postValue(repository.guardiasPendientes())
        }
    }

    fun guardiasProfesor(id: Int) {
        viewModelScope.launch {
            guardiasProfesor.postValue(repository.guardiasProfesor(id))
        }
    }

    //método para cruzar las guardias pendientes con las guardias de un profesor
    fun posiblesGuardias(id: Int): MutableList<Guardia> {
        var guardiasPendientes = guardiasPendientes.value
        var guardiasProfesor = guardiasProfesor.value
        val guardiasRealizables: MutableList<Guardia> = mutableListOf()
        if (guardiasPendientes != null) {
            for (guardia in guardiasPendientes) {
                if (guardiasProfesor != null) {
                    for (guardiaProfesor in guardiasProfesor) {
                        if (guardia.fecha.equals(guardiaProfesor.fecha) && (guardia.horario.equals(
                                guardiaProfesor.horario
                            ))
                        )
                            guardiasRealizables.add(guardia)
                    }
                }
            }

        }
        return guardiasRealizables
    }


    fun crearGuardia(nuevaGuardia: Guardia): LiveData<Guardia> {
        val resultado = MutableLiveData<Guardia>()
        viewModelScope.launch {
            val respuesta = GuardiasApi.retrofitService.crearGuardia(nuevaGuardia).await()
            resultado.value = respuesta
        }
        return resultado
    }

    fun cambiarEstadoGuardia(id: Int, estado: String){
        viewModelScope.launch {
            guardia.postValue(repository.cambiarEstadoGuardia(id, estado))
        }
    }


    //métodos de avisos
    fun cargarlistaAvisos() {
        viewModelScope.launch {
            try {
                listaAvisos.postValue(repository.getAvisos())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun guardarAviso(aviso: Aviso_Guardia) {
        avisoLiveData.value = aviso
    }

    fun getAviso(id: Int) {
        viewModelScope.launch {
            try {
                avisoLiveData.postValue(repository.getAviso((id)))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun crearAviso(avisoGuardia: Aviso_Guardia): Aviso_Guardia? =
        repository.crearAviso(avisoGuardia)

    suspend fun actualizarAviso(id: Int, nuevo: Aviso_Guardia): LiveData<Aviso_Guardia> {
        val resultado = MutableLiveData<Aviso_Guardia>()
        viewModelScope.launch {
            val respuesta = GuardiasApi.retrofitService.actualizarAviso(nuevo, id).await()
            resultado.value = respuesta
        }
        return resultado
    }

    fun borrarAviso(id: Int) {
        viewModelScope.launch {
            val respuesta = GuardiasApi.retrofitService.borrarAviso(id).await()
        }
    }

    //métodos de profesores
    fun cargarlistaProfesores() {
        viewModelScope.launch {
            try {
                listaProfesores.postValue(repository.getProfesores())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun obtenerUno(id: Int): Profesor? {
        var profesorBuscado: Profesor? = null
        viewModelScope.launch {
            profesorBuscado = GuardiasApi.retrofitService.obtenerUno(id)

        }

        return profesorBuscado
    }

    fun ProfesorVM(profesor: Profesor) = profesorLiveData.postValue(profesor)

    //método para recoger el horario de guardias de un profesor
    fun getHorarioGuardias(profesor: Int): HorarioGuardias? {
        viewModelScope.launch {
            try {
                horarioGuardias.postValue(repository.getHorarioGuardia(profesor))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return horarioGuardias.value
    }

    //método para obtener las horas que trabaja un profesor
    fun getHorarioProfesor(id: Int, dia: Int): List<Horario>? {
        viewModelScope.launch {
            listaHorariosLiveData.postValue(repository.getHorarioProfesor(id, dia))
        }
        return listaHorariosLiveData.value
    }

    //método para obtener la lista de guardias de un día
    fun getGuardiasDia(dia: LocalDate): List<Guardia>? {
        viewModelScope.launch {
            try {
                listaGuardias.postValue(repository.getGuardiasDia(dia))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return listaGuardias.value
    }
}



