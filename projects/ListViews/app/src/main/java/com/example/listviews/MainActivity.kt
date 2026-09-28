package com.example.listviews

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.listviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var cursos: Array<String>
    private lateinit var cursosDesc: Array<String>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()
    }

    private fun setupViews(){
        cursos = arrayOf(getString(R.string.nome_tsi), getString(R.string.nome_eng), getString(R.string.nome_ads), getString(R.string.nome_mat));
        cursosDesc = arrayOf(getString(R.string.desc_tsi), getString(R.string.desc_eng), getString(R.string.desc_ads), getString(R.string.desc_mat))
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cursos
        )
        binding.listaNomes.adapter = adapter
    }

    private fun setupListeners(){
        binding.listaNomes.setOnItemClickListener{parent, view, position, id ->
            val pos = position
            mostrarMensagem(cursosDesc[pos])
        }
    }

    private fun mostrarMensagem(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }
}