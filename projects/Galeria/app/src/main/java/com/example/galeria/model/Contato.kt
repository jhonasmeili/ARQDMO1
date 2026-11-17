package com.example.galeria.model

import java.io.Serializable

data class Contato(
    val foto: String,
    val nome: String,
    val telefone: String,
    val email: String
) : Serializable