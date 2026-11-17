package com.example.persistencia.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.persistencia.model.Contato

@Dao
interface ContatoDAO {

    @Insert
    suspend fun inserir(contato: Contato)

    @Query("SELECT * FROM contato ORDER BY nome ASC")
    suspend fun listarTodos(): List<Contato>

    @Query("SELECT * FROM contato WHERE nome LIKE :filtro ORDER BY nome ASC")
    suspend fun filtrarPorNome(filtro: String): List<Contato>

    @Delete
    suspend fun deletar(contato: Contato)

    @Update
    suspend fun atualizar(contato: Contato)
}