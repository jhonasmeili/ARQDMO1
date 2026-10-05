package com.example.listviewsextra.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewsextra.R
import com.example.listviewsextra.databinding.ActivityMainBinding
import com.example.listviewsextra.model.Contato
import com.example.listviewsextra.adapter.ContatoAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contatos: List<Contato>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }
    fun loadData(){
        contatos = listOf(
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
        ).sortedBy { it.nome }
    }
    fun setupViews(){
        val adapter = ContatoAdapter(this, contatos)
        binding.listViewContatos.adapter = adapter
    }
    fun setupListeners(){
        binding.listViewContatos.setOnItemClickListener { _, _, position, _ ->
            val contato = contatos[position]
            Toast.makeText(this, "Contato: ${contato.nome}", Toast.LENGTH_SHORT).show()
        }
    }
}