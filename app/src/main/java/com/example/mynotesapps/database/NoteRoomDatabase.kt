package com.example.mynotesapps.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null
        @OptIn(InternalCoroutinesApi::class)
        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        NoteRoomDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NoteRoomDatabase
        }
    }
}


