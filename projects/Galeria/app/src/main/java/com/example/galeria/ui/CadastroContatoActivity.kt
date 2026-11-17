package com.example.galeria.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.galeria.R
import com.example.galeria.databinding.ActivityCadastroContatoBinding
import com.example.galeria.model.Contato

class CadastroContatoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroContatoBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroContatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val nome = binding.editNome.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()

            if(nome.isNotBlank() && telefone.isNotBlank() && email.isNotBlank()){
                val novoContato = Contato(
                    foto = uriSelecionada,
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