package com.example.persistencia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.persistencia.dao.ContatoDao
import com.example.persistencia.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AgendaDatabase : RoomDatabase () {
    abstract fun contatoDao(): ContatoDao

    companion object{
        @Volatile
        private var INSTANCE: AgendaDatabase? = null

        fun getInstance(context: Context): AgendaDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AgendaDatabase::class.java, "agenda_database").build()

                INSTANCE = instance
                instance
            }
        }
    }
}