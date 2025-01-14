package com.luanadev.ollaapp.database

import androidx.room.*
import com.luanadev.ollaapp.data.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insere(usuario: Usuario)

    @Query("SELECT * FROM Usuario")
    fun buscaTodos(): Flow<List<Usuario>>

    @Query("SELECT * FROM Usuario WHERE idUsuario = :usuario")
    fun buscaPorId(usuario: String) : Flow<Usuario?>

    @Update
    suspend fun atualiza(usuario: Usuario)

    @Delete
    suspend fun apaga(usuario: Usuario)
}