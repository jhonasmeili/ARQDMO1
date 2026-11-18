package com.example.catalogodecursos.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catalogodecursos.R
import com.example.catalogodecursos.adapter.CursoAdapterMain
import com.example.catalogodecursos.databinding.ActivityMainBinding
import com.example.catalogodecursos.db.CursoDatabase
import com.example.catalogodecursos.model.Curso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cursos: MutableList<Curso>
    private lateinit var adapter: CursoAdapterMain
    private lateinit var db: CursoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupRecyclerView()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData(){
        cursos = mutableListOf()
        db = CursoDatabase.getInstance(this)

        lifecycleScope.launch(Dispatchers.IO){
            cursos = db.cursoDao().listarTodos().toMutableList()

            withContext(Dispatchers.Main){
                adapter.updateLista(cursos)
            }
        }
    }

    fun setupRecyclerView(){
        adapter = CursoAdapterMain(cursos){ curso ->
            val intent = Intent(this, DetalheCursoActivity::class.java)
            intent.putExtra("curso", curso)
            startActivity(intent)
        }

        binding.rvListaCursos.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = this@MainActivity.adapter
        }
    }

    fun setupListeners(){
        binding.btnAdicionar.setOnClickListener(){
            val intent = Intent(this, CadastroCursoActivity::class.java)
            startActivity(intent)
        }

        binding.edtBuscaCurso.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtro = s.toString().lowercase()
                val filtrados = cursos.filter {
                    it.nome.lowercase().contains(filtro)
                }

                adapter.updateLista(filtrados)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}