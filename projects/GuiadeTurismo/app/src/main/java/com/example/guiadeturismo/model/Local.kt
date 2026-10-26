package com.example.guiadeturismo.model

import java.io.Serializable

data class Local(
    val foto: Int,
    val nome: String,
    val desc: String,
    val coord: String
) : Serializable
