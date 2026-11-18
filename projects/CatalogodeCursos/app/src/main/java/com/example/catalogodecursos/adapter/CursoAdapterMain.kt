package com.example.catalogodecursos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogodecursos.databinding.ActivityMainBinding
import com.example.catalogodecursos.databinding.ItemCursoBinding
import com.example.catalogodecursos.model.Curso

class CursoAdapterMain(
    private var cursos: List<Curso>,
    private val onClick: (Curso) -> Unit
): RecyclerView.Adapter<CursoAdapterMain.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCursoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cursos[position])
    }

    override fun getItemCount(): Int = cursos.size

    fun updateLista(novosCursos: List<Curso>){
        this.cursos = novosCursos
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemCursoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(curso: Curso){
            binding.imgFoto.setImageURI(curso.foto.toUri())
            binding.tvNome.text = curso.nome
            binding.tvPeriodo.text = curso.periodo
            binding.root.setOnClickListener{onClick(curso)}
        }
    }
}