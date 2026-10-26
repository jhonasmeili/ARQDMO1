package com.example.guiadeturismo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.guiadeturismo.databinding.ActivityDetalheLocalBinding
import com.example.guiadeturismo.model.Local
import kotlinx.coroutines.selects.SelectInstance

class DetalheLocalActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetalheLocalBinding
    private lateinit var local: Local
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheLocalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }

    fun loadData(){
        local = intent.getSerializableExtra("local") as Local
    }

    fun setupViews(){
        binding.tvNome.text = local.nome
        binding.tvDesc.text = local.desc
        binding.imgFoto.setImageResource(local.foto)
    }

    private fun setupListeners(){
        binding.btnLigar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = "tel:(16) 99999-9996".toUri()
            startActivity(intent)
        }
        binding.btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto:contato@ifsp.edu.br".toUri()
            startActivity(intent)
        }
        binding.btnMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = local.coord.toUri()
            startActivity(intent)
        }
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}