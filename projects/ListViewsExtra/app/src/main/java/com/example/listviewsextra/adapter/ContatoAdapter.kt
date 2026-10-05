package com.example.listviewsextra.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.listviewsextra.R
import com.example.listviewsextra.model.Contato


class ContatoAdapter(
    private val context: Context,
    private val lista: List<Contato>
) : ArrayAdapter<Contato>(context, 0, lista) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_contato, parent, false)
        val contato = lista[position]
        val imgFoto = itemView.findViewById<ImageView>(R.id.imgFoto)
        val tvNome = itemView.findViewById<TextView>(R.id.tvNome)
        val tvTelefone = itemView.findViewById<TextView>(R.id.tvTelefone)
        val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
        imgFoto.setImageResource(contato.foto)
        tvNome.text = contato.nome
        tvTelefone.text = contato.telefone
        tvEmail.text = contato.email
        return itemView
    }
}