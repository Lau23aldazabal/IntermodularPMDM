package es.grupo04.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import es.grupo04.guardiascentro.R
import es.grupo04.guardiascentro.databinding.FragmentAvisoBinding
import es.grupo04.modelo.Aviso_Guardia
import es.grupo04.modelo.Horario
import es.grupo04.modelo.Profesor
import es.grupo04.viewModel.GuardiasViewModel
import kotlinx.coroutines.launch
import java.sql.Time
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar


class FragmentAviso : Fragment() {
    private lateinit var binding: FragmentAvisoBinding
    private val guardiasViewModel: GuardiasViewModel by activityViewModels()
    private lateinit var fechaString: String
    private lateinit var fechaLd: LocalDate


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvisoBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var avisoGuardar = Aviso_Guardia()
        arguments?.let {
            fechaString = it.getString("fechaAviso").toString()
            fechaLd = LocalDate.parse(fechaString)
        }
        binding.textViewProfesor.text = guardiasViewModel.profesorLiveData.value?.nombre+"  "+guardiasViewModel.profesorLiveData.value?.ape1
        binding.textViewFechaAviso.text = fechaString
        binding.textViewHoraAviso.text= LocalTime.now().toString()

        binding.btnConfirmarAviso.setOnClickListener {
            avisoGuardar.fecha_aviso = fechaLd
            if (binding.checkBoxanulado.isChecked) {
                avisoGuardar.anulado = true;
            } else {
                avisoGuardar.anulado = false;
            }
            if (binding.checkBoxConfirmado.isChecked) {
                avisoGuardar.confirmado = true
            } else {
                avisoGuardar.confirmado = false
            }
            avisoGuardar.profesor = guardiasViewModel.profesorLiveData.value!!
            avisoGuardar.motivo = binding.textViewMotivo.text.toString()
            avisoGuardar.observaciones = binding.textViewObservaciones.toString()
            /*lifecycleScope.launch {
                guardiasViewModel.crearAviso(avisoGuardar)
            }*/
            val accion = FragmentAvisoDirections.deAvisoAHoras(aviso = avisoGuardar)
            findNavController().navigate(accion)
        }

    }
}


