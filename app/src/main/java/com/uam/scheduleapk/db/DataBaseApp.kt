package com.uam.scheduleapk.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.uam.scheduleapk.entity.Agenda


@Database(
    entities = [Agenda::class],
    version = 1
)
abstract class DataBaseApp : RoomDatabase() {
    abstract fun agendaDAO():AgendaDAO


    companion object {
        @Volatile
        private var INSTANCE: DataBaseApp? = null

        fun getDatabase(context: Context): DataBaseApp {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DataBaseApp::class.java,
                    "AgendaDb")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}