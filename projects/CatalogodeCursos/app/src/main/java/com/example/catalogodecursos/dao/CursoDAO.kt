package com.example.catalogodecursos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.catalogodecursos.model.Curso

@Dao
interface CursoDAO {

    @Insert
    suspend fun inserir(curso: Curso)

    @Query("SELECT * FROM curso ORDER BY nome ASC")
    suspend fun listarTodos(): List<Curso>

    @Query("SELECT * FROM curso WHERE nome LIKE :filtro ORDER BY nome ASC")
    suspend fun filtrarPorNome(filtro: String): List<Curso>

    @Delete
    suspend fun deletar(curso: Curso)

    @Update
    suspend fun atualizar(curso: Curso)
}