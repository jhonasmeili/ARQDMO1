package com.example.recyclerview.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.recyclerview.R
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.databinding.ActivityCadastroContatoBinding
import com.example.recyclerview.model.Contato

class CadastroContatoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroContatoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()

    }

    fun setupListeners() {
        binding.btnSalvar.setOnClickListener(){
            val nome = binding.editNome.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()

            if(nome.isNotBlank() && telefone.isNotBlank() && email.isNotBlank()){
                val novoContato = Contato(
                    foto = R.drawable.ic_launcher_foreground,
                    nome = nome,
                    telefone = telefone,
                    email = email
                )

                val resultIntent = Intent().apply {
                    putExtra("contato", novoContato)
                }

                setResult(Activity.RESULT_OK, resultIntent)
                finish()

            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVoltar.setOnClickListener(){
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}