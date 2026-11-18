package com.example.catalogodecursos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "curso")
data class Curso(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val foto: String,
    val nome: String,
    val descricao: String,
    val periodo: String,
    val link: String
) : Serializable