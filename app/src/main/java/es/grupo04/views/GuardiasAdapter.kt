package es.grupo04.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.grupo04.guardiascentro.R
import es.grupo04.guardiascentro.databinding.ItemGuardiaBinding
import es.grupo04.modelo.Guardia
import es.grupo04.viewModel.GuardiasViewModel


class GuardiasAdapter(
    private val listaGuardias: List<Guardia>,
    private val guardiasViewModel: GuardiasViewModel

    ) : RecyclerView.Adapter<GuardiasAdapter.GuardiaViewHolder>() {

    class GuardiaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemGuardiaBinding.bind(view)
        fun bind(guardia: Guardia, guardiasViewModel:GuardiasViewModel) {
            binding.textViewAula.text = guardia.aula
            binding.textViewHora.text = guardia.hora
            binding.textViewGrupo.text = guardia.grupo
            binding.checkBoxRealizada.setOnClickListener {
                if(binding.checkBoxRealizada.isChecked){
                    guardiasViewModel.cambiarEstadoGuardia(guardia.id,"R")
                }else{
                    guardiasViewModel.cambiarEstadoGuardia(guardia.id,"C")
                }
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuardiasAdapter.GuardiaViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_guardia, parent, false)
        return GuardiasAdapter.GuardiaViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: GuardiasAdapter.GuardiaViewHolder, position: Int) {
        val guardia = listaGuardias.get(position)
        if (guardia != null) {
            holder.bind(guardia,guardiasViewModel)
        }
    }
    override fun getItemCount(): Int = listaGuardias.size
}