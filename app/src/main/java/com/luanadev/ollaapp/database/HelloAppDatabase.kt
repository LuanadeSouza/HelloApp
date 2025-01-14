package com.luanadev.ollaapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.luanadev.ollaapp.data.Contato
import com.luanadev.ollaapp.database.ContatoDao
import com.luanadev.ollaapp.database.converters.Converters

@Database(entities = [Contato::class], version = 1)
@TypeConverters(Converters::class)
abstract class HelloAppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao
}