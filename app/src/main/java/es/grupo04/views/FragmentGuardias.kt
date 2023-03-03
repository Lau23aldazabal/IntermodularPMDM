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
import es.grupo04.guardiascentro.databinding.FragmentGuardiasBinding
import es.grupo04.modelo.EnumEstado
import es.grupo04.modelo.Guardia
import es.grupo04.modelo.Horario
import es.grupo04.modelo.Profesor
import es.grupo04.viewModel.GuardiasViewModel
import java.time.LocalDate


class FragmentGuardias : Fragment() {
    private lateinit var binding: FragmentGuardiasBinding
    private val guardiasViewModel: GuardiasViewModel by activityViewModels()
    private var profesor: Profesor?=null
    private lateinit var navController: NavController

    private lateinit var fecha: LocalDate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuardiasBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController=findNavController()
      /*arguments?.let {
            profesor = it.getSerializable("profesor") as Profesor
            var stringfecha: String? = it.getString("fecha")
            var fecha = LocalDate.parse(stringfecha)
        }*/

        profesor = guardiasViewModel.profesorLiveData.value
        var posiblesGuardias=guardiasViewModel.posiblesGuardias(1)
        var guardia=Guardia(1,10,4,EnumEstado.R, LocalDate.now(),1, horario = Horario(1, 1, 1, 5, "A2", "ESO1A", "MAT", 1),"1",1,"ESO1B","A2","Enfermedad")
        var guardia1=Guardia(2,10,4,EnumEstado.R, LocalDate.now(),1, horario = Horario(1, 1, 1, 5, "A2", "ESO1B", "MAT", 1),"1",1,"ESO1B","A2","Enfermedad")
        posiblesGuardias.add(guardia)
        posiblesGuardias.add(guardia1)
        binding.recyclerViewGuardias.layoutManager = LinearLayoutManager(context);
        binding.recyclerViewGuardias.adapter = GuardiasAdapter(posiblesGuardias, guardiasViewModel)
        binding.btnVolver.setOnClickListener {
            navController.navigate(FragmentGuardiasDirections.actionFragmentGuardiasToFragmentCalendario())
        }
        binding.btnConfirmarGuardias.setOnClickListener {
            val builder = context?.let { AlertDialog.Builder(it) }
            builder?.setTitle("Guardias")
            builder?.setMessage("Se han confirmado " + 2 + " guardias")
            builder?.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            builder?.show()
        }
        }
        }

