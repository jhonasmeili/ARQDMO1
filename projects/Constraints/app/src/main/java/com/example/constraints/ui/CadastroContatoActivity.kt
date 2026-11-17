package com.example.constraints.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.constraints.databinding.ActivityCadastroContatoBinding
import com.example.constraints.model.Contato

class CadastroContatoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroContatoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()

    }

    fun setupListeners() {
        binding.btnEntrar.setOnClickListener(){
            finish()
        }
    }
}