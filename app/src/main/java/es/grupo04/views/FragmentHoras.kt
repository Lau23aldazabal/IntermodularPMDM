package es.grupo04.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.grupo04.guardiascentro.databinding.FragmentHorasBinding
import es.grupo04.modelo.Aviso_Guardia
import es.grupo04.modelo.EnumEstado
import es.grupo04.modelo.Guardia
import es.grupo04.modelo.Horario
import es.grupo04.viewModel.GuardiasViewModel
import java.security.Guard
import java.time.LocalDate


class FragmentHoras : Fragment() {
    private lateinit var binding: FragmentHorasBinding
    private val guardiasViewModel: GuardiasViewModel by activityViewModels()
    private var listaHorarios: MutableList<Horario> = mutableListOf()
    private var listaGuardias: MutableList<Guardia> = mutableListOf()
    private lateinit var navController: NavController

    private lateinit var aviso: Aviso_Guardia
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHorasBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //aquí tenemos que cargar el recycler view con las horas que le corresponden al profesor ese día
        //cogemos el aviso del nav
        arguments?.let {
            aviso = it.getSerializable("aviso") as Aviso_Guardia
        }
        navController=findNavController()
        //cogemos el día del aviso
        var dia: Int? = aviso.fecha_aviso?.dayOfWeek?.value

//cogemos la lista de horas del profesor
        var id = guardiasViewModel.profesorLiveData.value?.id
        if (id != null) {
            //   listaHorarios= guardiasViewModel.getHorarioProfesor(id,dia!!)!!
            val horario = Horario(1, 1, 5, 1, "A2", "ESO1A", "MAT", 1)
            val horario2 = Horario(2, 1, 5, 2, "A1", "ESO2B", "LEN", 1)
            val horario3 = Horario(3, 1, 5, 3, "A3", "ESO1B", "ING", 1)
            val horario4 = Horario(4, 1, 5, 4, "A3", "ESO2A", "TIC", 1)
            val horario5 = Horario(5, 1, 5, 5, "A1", "ESO1A", "CIE", 1)
            val horario6 = Horario(6, 1, 5, 6, "A2", "ESO2B", "MAT", 1)
            listaHorarios.add(horario)
            listaHorarios.add(horario2)
            listaHorarios.add(horario3)
            listaHorarios.add(horario4)
            listaHorarios.add(horario5)
            listaHorarios.add(horario6)
            //Meto horarios a mano para probar porque el metodo getHorarioProfesor() devuelve null
        }
        binding.recyclerViewHoras.layoutManager = LinearLayoutManager(context);
        binding.recyclerViewHoras.adapter = HorarioAdapter(listaHorarios, guardiasViewModel)
        var adapter = HorarioAdapter(listaHorarios, guardiasViewModel)
        binding.btnConfirmarHoras.setOnClickListener {
            var contador = 0
            for (horario in listaHorarios) {
                guardiasViewModel.aniadirhorario(horario)
            }

    /*        guardiasViewModel.listaHorasConfirmadas.observe(viewLifecycleOwner) { lista ->
                var contadorHorarios = 1
                var guardia: Guardia? = null
                var horario: Horario = lista.get(contadorHorarios)
                guardia?.aviso = aviso.id_aviso
                guardia?.hora = horario.hora.toString()
                guardia?.aula = horario.aula
                guardia?.grupo = horario.grupo
                guardia?.prof_falta = horario.profesor
                guardia?.Estado = EnumEstado.C
                guardia?.dia_semana = horario.dia_semana
                if (guardia != null) {
                    guardiasViewModel.crearGuardia(guardia)
                    contador++;
                }
                contadorHorarios++*/

                val builder = context?.let { AlertDialog.Builder(it) }
                builder?.setTitle("Guardias")
                builder?.setMessage("Se han creado " + /*contador*/ 2 + " guardias")
                builder?.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                builder?.show()
           // }

        }
        binding.btnDiaCompleto.setOnClickListener{
            //Seleccionar todos los checkBox
        }
        binding.btnVolver.setOnClickListener {
            navController.navigate(FragmentHorasDirections.actionFragmentHorasToFragmentCalendario()) }
        }
    }


