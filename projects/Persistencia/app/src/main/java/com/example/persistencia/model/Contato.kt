package com.example.persistencia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contato")
data class Contato(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val foto: String,
    val nome: String,
    val telefone: String,
    val email: String
) : Serializable