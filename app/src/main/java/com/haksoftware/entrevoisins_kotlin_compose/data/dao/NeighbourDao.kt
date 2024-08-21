package com.haksoftware.entrevoisins_kotlin_compose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity

interface NeighbourDao {
    @Transaction
    @Query("select * from neighbour where idNeighbour = :idNeighbour")
    fun getNeighbour(idNeighbour: Int): NeighbourEntity
    @Transaction
    @Query("select * from neighbour")
    fun getAllNeighbour(): LiveData<List<NeighbourEntity>>
    @Insert
    fun insertNeighbour(neighbour: NeighbourEntity): Long
    @Update
    fun updateNeighbour(neighbour: NeighbourEntity) : Int
    @Query("DELETE FROM neighbour where idNeighbour = :realtor")
    fun deleteNeighbour(realtor: Int): Int
}