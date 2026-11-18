package com.example.catalogodecursos.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.catalogodecursos.databinding.ActivityDetalheCursoBinding
import com.example.catalogodecursos.model.Curso

class DetalheCursoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheCursoBinding
    private lateinit var curso: Curso
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData(){
        curso = intent.getSerializableExtra("curso") as Curso
    }

    private fun setupViews(){
        binding.imgFoto.setImageURI(curso.foto.toUri())
        binding.tvNome.text = curso.nome
        binding.tvDesc.text = curso.descricao
        binding.tvPeriodo.text = curso.periodo
    }

    private fun setupListeners(){
        binding.btnInscrever.setOnClickListener(){
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = curso.link.toUri()
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener(){
            finish()
        }
    }
}