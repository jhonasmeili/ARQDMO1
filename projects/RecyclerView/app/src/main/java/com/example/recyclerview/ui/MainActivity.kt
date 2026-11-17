package com.example.recyclerview.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.R
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Contato
import com.example.recyclerview.adapter.ContatoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contatos: MutableList<Contato>
    private lateinit var adapter: ContatoAdapter
    private lateinit var launcherCadastro : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupRecyclerView()
        setupLauncherCaastro()
        setupListeners()
    }
    fun loadData(){
        contatos = mutableListOf(
            Contato(
                R.drawable.minkey,
                "Minkey",
                "(16) 98888-7777",
                "minkey@email.com"
            ),
            Contato(
                R.drawable.mennie,
                "Mennie",
                "(16) 97777-6666",
                "mennie@email.com"
            ),
            Contato(
                R.drawable.patetla,
                "Patleta",
                "(16) 96666-5555",
                "patleta@email.com"
            ),
            Contato(
                R.drawable.truco,
                "Truco",
                "(16) 98888-7777",
                "truco@email.com"
            ),
            Contato(
                R.drawable.donaldo,
                "Donaldo",
                "(16) 97777-6666",
                "donaldo@email.com"
            ),
            Contato(
                R.drawable.carlos,
                "Carlos",
                "(16) 96666-5555",
                "carlos@email.com"
            )
        ).sortedBy { it.nome }.toMutableList()
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
    }
}