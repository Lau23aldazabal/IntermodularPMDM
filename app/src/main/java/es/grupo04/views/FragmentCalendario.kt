package es.grupo04.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import es.grupo04.guardiascentro.databinding.FragmentCalendarioBinding
import es.grupo04.viewModel.GuardiasViewModel
import java.time.LocalDate

import java.util.*

class FragmentCalendario : Fragment() {
    private lateinit var binding: FragmentCalendarioBinding
    private lateinit var navController: NavController
    private val guardiasViewModel: GuardiasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
     //   val hoy = Calendar.getInstance()
     //   var fechaLD: LocalDate= LocalDate.MIN
        binding.datePicker.firstDayOfWeek=2
        navController=findNavController()
        val dia = binding.datePicker.dayOfMonth
        val mes = binding.datePicker.month+1
        val anio = binding.datePicker.year
        val fecha = LocalDate.of(anio, mes, dia)
        guardiasViewModel.fechaAviso(fecha)
        //   guardiasViewModel.fechaAviso(fechaLD)
        binding.btnGenerarAviso.setOnClickListener {
            navController.navigate(FragmentCalendarioDirections.deCalendarioAAvisos (fechaAviso = fecha.toString()))
        }
        binding.btnVerGuardias.setOnClickListener {
            navController.navigate(FragmentCalendarioDirections.deCalendarioAGuardias()) }
        }
    }



