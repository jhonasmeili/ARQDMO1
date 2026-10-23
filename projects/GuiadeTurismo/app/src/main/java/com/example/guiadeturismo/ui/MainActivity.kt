package com.example.guiadeturismo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guiadeturismo.R
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



    }

    fun lodData(){

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
        ).sortedBy{it.nome}
    }
}