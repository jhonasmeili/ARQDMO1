package com.example.catalogodecursos.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.catalogodecursos.R
import com.example.catalogodecursos.databinding.ActivityCadastroCursoBinding
import com.example.catalogodecursos.db.CursoDatabase
import com.example.catalogodecursos.model.Curso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroCursoActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCadastroCursoBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    private lateinit var db: CursoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroCursoBinding .inflate(layoutInflater)
        setContentView(binding.root)

        db = CursoDatabase.getInstance(this)
        setupLauncher()
        setupListeners()

    }

    private fun setupLauncher(){
        launcherGaleria = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ){uri ->
            if(uri != null){
                contentResolver.takePersistableUriPermission(
                    uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

                uriSelecionada = uri.toString()
                binding.imgNovoCurso.setImageURI(uri)
            }
        }
    }

    private fun setupListeners(){
        binding.imgNovoCurso.setOnClickListener(){
            launcherGaleria.launch(arrayOf("image/*"))
        }

        binding.btnSalvar.setOnClickListener(){
            val foto = uriSelecionada
            val nome = binding.edtNome.text.toString()
            val desc = binding.edtDesc.text.toString()
            val periodo = binding.edtPeriodo.text.toString()
            val link = resources.getString(R.string.link)

            if(nome.isNotBlank() && desc.isNotBlank() && periodo.isNotBlank()){
                val novoCurso = Curso(
                    foto = foto,
                    nome = nome,
                    descricao = desc,
                    periodo = periodo,
                    link = link
                )

                salvarNoBanco(novoCurso)

            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarNoBanco(curso: Curso){
        lifecycleScope.launch(Dispatchers.IO){
            db.cursoDao().inserir(curso)
            finish()
        }
    }
}