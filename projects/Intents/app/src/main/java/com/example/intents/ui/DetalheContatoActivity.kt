package com.example.intents.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.intents.databinding.ActivityDetalheContatoBinding

class DetalheContatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheContatoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }
    private fun setupListeners() {
        binding.btnLigar.setOnClickListener {
            Toast
                .makeText(this, "Ligar para o contato", Toast.LENGTH_SHORT)
                .show()
        }
        binding.btnEmail.setOnClickListener {
            Toast
                .makeText(this, "Enviar email para o contato", Toast.LENGTH_SHORT)
                .show()
        }
        binding.btnVoltar.setOnClickListener {
            Toast.makeText(this, "Voltar", Toast.LENGTH_SHORT).show()
        }
    }
}