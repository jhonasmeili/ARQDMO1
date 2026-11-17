package com.example.galeria.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galeria.R
import com.example.galeria.databinding.ActivityMainBinding
import com.example.galeria.model.Contato
import com.example.galeria.adapter.ContatoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contatos: MutableList<Contato>
    private lateinit var adapter: ContatoAdapter
    private lateinit var launcherCadastro : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contatos = mutableListOf()
        setupRecyclerView()
        setupLauncherCaastro()
        setupListeners()
    }
    fun setupRecyclerView(){
        adapter = ContatoAdapter(contatos) { contato ->
            val intent = Intent(this, DetalheContatoActivity::class.java)
            intent.putExtra("contato", contato)
            startActivity(intent)
        }
        binding.listViewContatos.layoutManager = LinearLayoutManager(this)
        binding.listViewContatos.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.listViewContatos.adapter = adapter

        //Igual aos itens binding.listViewContatos de cima

        //binding.listViewContatos.apply{
        //    layoutManager = LinearLayoutManager(this@MainActivity)
        //    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        //    adapter = this@MainActivity.adapter
        //}
    }
    fun setupLauncherCaastro(){
        launcherCadastro = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {result ->
            if(result.resultCode == Activity.RESULT_OK){
                val data = result.data
                val novoContato = data?.getSerializableExtra("contato", Contato::class.java)

                if(novoContato != null){
                    contatos.add(novoContato)
                    adapter.notifyItemInserted(contatos.size -1)
                }
            }
        }
    }
    fun setupListeners(){
        binding.btnAdicionar.setOnClickListener{
            val intent = Intent(this, CadastroContatoActivity::class.java)
            launcherCadastro.launch(intent)
        }

        binding.edtFiltro.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtro = s.toString().lowercase()
                val filtrados = contatos.filter{
                    it.nome.lowercase().contains(filtro)
                }

                adapter.updateLista(filtrados)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}