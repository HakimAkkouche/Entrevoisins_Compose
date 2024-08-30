package com.haksoftware.entrevoisins_kotlin_compose.data.repository

import android.content.Context
import android.util.Log
import com.haksoftware.entrevoisins_kotlin_compose.data.dao.NeighbourDao
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NeighboursRepository @Inject constructor(
    val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
    private val neighbourDao: NeighbourDao
) {

    suspend fun getNeighbour(neighbourId: Int): NeighbourEntity {
        return withContext(ioDispatcher) { neighbourDao.getNeighbour(neighbourId)}
    }

    fun getAllNeighbourFlow(): Flow<List<NeighbourEntity>> {
        return neighbourDao.getAllNeighbourFlow()
    }
    fun getAllFavoritesNeighbourFlow(): Flow<List<NeighbourEntity>> {
        return neighbourDao.getAllFavoritesNeighbourFlow()
    }
    suspend fun insertNeighbour(neighbourEntity: NeighbourEntity): Long {
        return withContext(ioDispatcher) {
            neighbourDao.insertNeighbour(neighbourEntity)
        }
    }
    suspend fun updateNeighbour(neighbourEntity: NeighbourEntity): Int {
        return withContext(ioDispatcher) {
            neighbourDao.updateNeighbour(neighbourEntity)
        }
    }
    suspend fun deleteNeighbour(neighbourId: Int): Int {
        return withContext(ioDispatcher) {
            neighbourDao.deleteNeighbour(neighbourId)
        }
    }
}