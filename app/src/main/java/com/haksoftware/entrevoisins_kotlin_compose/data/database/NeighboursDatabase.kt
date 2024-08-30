package com.haksoftware.entrevoisins_kotlin_compose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haksoftware.entrevoisins_kotlin_compose.data.dao.NeighbourDao
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity

@Database(entities = [NeighbourEntity::class], version = 1, exportSchema = false)
abstract class NeighboursDatabase: RoomDatabase() {

    abstract fun NeighbourDao(): NeighbourDao
}
