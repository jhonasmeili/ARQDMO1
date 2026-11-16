package com.example.persistencia.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.persistencia.R
import com.example.persistencia.databinding.ActivityMainBinding
import com.example.persistencia.model.Contato
import com.example.persistencia.adapter.ContatoAdapter
import com.example.persistencia.db.AgendaDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var contatos: List<Contato>
    private lateinit var db: AgendaDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }
    fun loadData(){

    }
    fun setupViews(){
        val adapter = ContatoAdapter(this, contatos)
        binding.listViewContatos.adapter = adapter
    }
    fun setupListeners(){
        binding.listViewContatos.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalheContatoActivity::class.java)
            intent.putExtra("contato", contatos[position])
            startActivity(intent)
        }
    }
}