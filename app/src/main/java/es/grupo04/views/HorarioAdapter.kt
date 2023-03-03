package es.grupo04.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.grupo04.guardiascentro.R
import es.grupo04.guardiascentro.databinding.ItemHorasBinding
import es.grupo04.modelo.Horario
import es.grupo04.viewModel.GuardiasViewModel


class HorarioAdapter(
    private val listaHoras: List<Horario>,
    private val guardiasViewModel: GuardiasViewModel
) : RecyclerView.Adapter<HorarioAdapter.HorasViewHolder>() {

    inner class HorasViewHolder(private val binding: ItemHorasBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(horario: Horario, guardiasViewModel: GuardiasViewModel) {
            binding.textViewHora.text = horario.hora.toString()
            binding.textViewAula.text = "Alua: " + horario.aula
            binding.textViewDiaSemana.text = "Día de la semana: " + horario.dia_semana.toString()

            binding.checkBoxAusencia.setOnClickListener(){
                if(binding.checkBoxAusencia.isChecked){
                    guardiasViewModel.aniadirhorario(horario)
                } else {
                    guardiasViewModel.quitarhorario(horario)
                }
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorasViewHolder {
        val binding = ItemHorasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorasViewHolder, position: Int) {
        val hora = listaHoras[position]
        holder.bind(hora, guardiasViewModel)
    }

    override fun getItemCount(): Int = listaHoras.size


}
