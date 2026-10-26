package com.example.guiadeturismo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guiadeturismo.R
import com.example.guiadeturismo.adapter.LocalAapter
import com.example.guiadeturismo.databinding.ActivityMainBinding
import com.example.guiadeturismo.model.Local

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var locais: List<Local>
    private lateinit var nomes: Array<String>
    private lateinit var desc: Array<String>
    private lateinit var coord: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        setupViews()
        setupListeners()
    }

    fun loadData(){

        nomes = resources.getStringArray(R.array.Locais)
        desc = resources.getStringArray(R.array.Desc)
        coord = resources.getStringArray(R.array.Coord)
        locais = listOf(
            Local(
                R.drawable.gen,
                nomes[0],
                desc[0],
                coord[0]
            ),
            Local(
                R.drawable.gen,
                nomes[1],
                desc[1],
                coord[1]
            ),
            Local(
                R.drawable.gen,
                nomes[2],
                desc[2],
                coord[2]
            ),
            Local(
                R.drawable.gen,
                nomes[3],
                desc[3],
                coord[3]
            ),
            Local(
                R.drawable.gen,
                nomes[4],
                desc[4],
                coord[4]
            ),
            Local(
                R.drawable.gen,
                nomes[5],
                desc[5],
                coord[5]
            ),
            Local(
                R.drawable.gen,
                nomes[6],
                desc[6],
                coord[6]
            ),
            Local(
                R.drawable.gen,
                nomes[7],
                desc[7],
                coord[7]
            ),
            Local(
                R.drawable.gen,
                nomes[8],
                desc[8],
                coord[8]
            ),
            Local(
                R.drawable.gen,
                nomes[9],
                desc[9],
                coord[9]
            )
        ).sortedBy{it.nome}
    }

    fun setupViews(){
        val adapter = LocalAapter(this, locais)
        binding.listViewLocais.adapter = adapter
    }

    fun setupListeners(){
        binding.listViewLocais.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetalheLocalActivity::class.java)
            intent.putExtra("local", locais[position])
            startActivity(intent)
        }
    }
}