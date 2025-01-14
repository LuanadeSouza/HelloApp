package com.luanadev.ollaapp.database

import androidx.room.*
import com.luanadev.ollaapp.data.Contato
import com.luanadev.ollaapp.data.Usuario
import com.luanadev.ollaapp.database.migrations.Migration3to4
import com.luanadev.ollaapp.database.converters.Converters

@Database(
    entities = [Contato::class, Usuario::class],
    version = 6,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(2, 3),
        AutoMigration(3, 4, Migration3to4::class),
        AutoMigration(4, 5)]
)
@TypeConverters(Converters::class)
abstract class HelloAppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
    abstract fun usuarioDao(): UsuarioDao
}
