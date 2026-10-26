package com.example.guiadeturismo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.guiadeturismo.R
import com.example.guiadeturismo.model.Local

class LocalAapter(
    private val context: Context,
    private val lista: List<Local>
) : ArrayAdapter<Local>(context, 0, lista){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_local, parent, false)
        val local = lista[position]
        val imgFoto = itemView.findViewById<ImageView>(R.id.imgFoto)
        val tvNome = itemView.findViewById<TextView>(R.id.tvNome)
        val tvDesc = itemView.findViewById<TextView>(R.id.tvDesc)
        val tvCoord = itemView.findViewById<TextView>(R.id.tvCoord)
        imgFoto.setImageResource(local.foto)
        tvNome.text = local.nome
        tvDesc.text = local.desc
        tvCoord.text = local.coord
        return itemView
    }
}