package es.grupo04.views

import android.os.Build
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import es.grupo04.guardiascentro.databinding.FragmentLoginBinding
import es.grupo04.modelo.GuardiasRepository
import es.grupo04.modelo.Profesor
import es.grupo04.viewModel.GuardiasViewModel
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.time.LocalDate

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val guardiasViewModel: GuardiasViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        binding.loginButton.setOnClickListener {
            login()
        }
    }

    fun login() {
        val user = binding.etUsuario.text.toString()
        val pwd = hashMD5(binding.etContrasenia.text.toString())
        lifecycleScope.launch {
            var profesor = guardiasViewModel.cargarProfesor(user, pwd)
            if (profesor != null) {
                guardiasViewModel.ProfesorVM(profesor)
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToFragmentCalendario())

            } else {
                val builder = context?.let { AlertDialog.Builder(it) }
                builder?.setTitle("Error")
                builder?.setMessage("Las credenciales no son correctas")
                builder?.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                builder?.show()


            }

        }
    }

    fun hashMD5(param: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digested = md.digest(param.toByteArray())
        return digested.joinToString("") {
            String.format("%02x", it)
        }
    }
}