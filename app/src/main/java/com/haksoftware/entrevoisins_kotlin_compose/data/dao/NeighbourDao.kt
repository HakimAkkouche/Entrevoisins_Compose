package com.haksoftware.entrevoisins_kotlin_compose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NeighbourDao {
    @Transaction
    @Query("select * from neighbour where idNeighbour = :idNeighbour")
    suspend fun getNeighbour(idNeighbour: Int): NeighbourEntity
    @Transaction
    @Query("select * from neighbour")
    fun getAllNeighbourFlow(): Flow<List<NeighbourEntity>>
    @Transaction
    @Query("select * from neighbour where isFavorite=1")
    fun getAllFavoritesNeighbourFlow(): Flow<List<NeighbourEntity>>
    @Insert
    suspend fun insertNeighbour(neighbour: NeighbourEntity): Long
    @Update
    suspend fun updateNeighbour(neighbour: NeighbourEntity) : Int
    @Query("DELETE FROM neighbour where idNeighbour = :neighbourId")
    suspend fun deleteNeighbour(neighbourId: Int): Int
}
