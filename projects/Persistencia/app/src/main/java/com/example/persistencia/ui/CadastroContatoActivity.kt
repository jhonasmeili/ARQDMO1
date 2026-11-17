package com.example.persistencia.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.persistencia.R
import com.example.persistencia.databinding.ActivityCadastroContatoBinding
import com.example.persistencia.db.AgendaDatabase
import com.example.persistencia.model.Contato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroContatoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroContatoBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    private lateinit var db: AgendaDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AgendaDatabase.getInstance(this)
        setupLauncher()
        setupListeners()

    }

    private fun setupLauncher(){
        launcherGaleria = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ){uri ->
            if (uri != null){
                contentResolver.takePersistableUriPermission(
                    uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

                uriSelecionada = uri.toString()
                binding.imgNovoContato.setImageURI(uri)
            }
        }
    }

    fun setupListeners() {
        binding.imgNovoContato.setOnClickListener(){
            launcherGaleria.launch(arrayOf("image/*"))
        }

        binding.btnSalvar.setOnClickListener(){
            val foto = uriSelecionada
            val nome = binding.editNome.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()

            if(nome.isNotBlank() && telefone.isNotBlank() && email.isNotBlank()){
                val novoContato = Contato(
                    foto = foto,
                    nome = nome,
                    telefone = telefone,
                    email = email
                )

                salvarNoBanco(novoContato)

            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVoltar.setOnClickListener(){
            finish()
        }
    }

    private fun salvarNoBanco(contato: Contato){
        lifecycleScope.launch(Dispatchers.IO){
            db.contatoDao().inserir(contato)
            finish()
        }
    }
}