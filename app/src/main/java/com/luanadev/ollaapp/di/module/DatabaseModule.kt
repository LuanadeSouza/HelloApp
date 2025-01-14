package com.luanadev.ollaapp.di.module

import android.content.Context
import androidx.room.Room
import com.luanadev.ollaapp.database.ContatoDao
import com.luanadev.ollaapp.database.HelloAppDatabase
import com.luanadev.ollaapp.database.UsuarioDao
import com.luanadev.ollaapp.database.migrations.MIGRATION_1_2
import com.luanadev.ollaapp.database.migrations.MIGRATION_5_6
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "helloApp.db"



@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HelloAppDatabase {
        return Room.databaseBuilder(
            context,
            HelloAppDatabase::class.java,
            DATABASE_NAME
        ).addMigrations(MIGRATION_1_2, MIGRATION_5_6)
            .build()
    }

    @Provides
    fun provideContatoDao(db: HelloAppDatabase): ContatoDao {
        return db.contatoDao()
    }

    @Provides
    fun provideUsuarioDao(db: HelloAppDatabase): UsuarioDao {
        return db.usuarioDao()
    }
}