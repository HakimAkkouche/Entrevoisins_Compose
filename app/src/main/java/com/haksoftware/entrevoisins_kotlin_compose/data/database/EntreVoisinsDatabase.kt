package com.haksoftware.entrevoisins_kotlin_compose.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.haksoftware.entrevoisins_kotlin_compose.data.dao.NeighbourDao

abstract class EntreVoisinsDatabase: RoomDatabase() {

    abstract fun NeighbourDao(): NeighbourDao

    companion object {
        @Volatile
        private var INSTANCE: EntreVoisinsDatabase? = null

        fun getDatabase(context: Context): EntreVoisinsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntreVoisinsDatabase::class.java,
                    "EntreVoisins.db"
                )
                    .createFromAsset("database/EntreVoisinsDatabase.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}