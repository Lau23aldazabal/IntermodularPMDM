package es.grupo04.views

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import es.grupo04.guardiascentro.R
import es.grupo04.guardiascentro.databinding.ActivityMainBinding
import es.grupo04.modelo.Profesor
import es.grupo04.viewModel.GuardiasViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val guardiasViewModel: GuardiasViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val fragmentCalendario = FragmentCalendario()
    private val fragmentGuardias = FragmentGuardias()
    private val fragmentAviso = FragmentAviso();
    private val loginFragment = LoginFragment();
    private var profesor: Profesor? = null
    private lateinit var fecha: Date


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //navController = findNavController().navigate();
        //inflado
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


}


