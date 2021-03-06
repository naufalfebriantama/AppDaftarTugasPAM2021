package com.naufal.appcrud.db.note

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naufal.appcrud.ui.main.Note

//Database annotation to specify the entities and set version
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate database if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getNoteDao() : NoteDao
}