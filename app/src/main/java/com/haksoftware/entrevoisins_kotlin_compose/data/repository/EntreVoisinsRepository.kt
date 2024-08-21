package com.haksoftware.entrevoisins_kotlin_compose.data.repository

import androidx.lifecycle.LiveData
import com.haksoftware.entrevoisins_kotlin_compose.data.dao.NeighbourDao
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class EntreVoisinsRepository(
    private val neighbourDao: NeighbourDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    companion object {
        @Volatile
        private var instance: EntreVoisinsRepository? = null

        fun getInstance(
            neighbourDao: NeighbourDao,
            ioDispatcher: CoroutineDispatcher
        ): EntreVoisinsRepository{
            return  instance ?: synchronized(this) {
                instance ?: EntreVoisinsRepository(neighbourDao, ioDispatcher)
                    .also { instance = it }
            }
        }
    }

    suspend fun getNeighbour(neighbourId: Int): NeighbourEntity {
        return withContext(ioDispatcher) { neighbourDao.getNeighbour(neighbourId)}
    }
    fun getAllNeighbour(): LiveData<List<NeighbourEntity>> {
        return neighbourDao.getAllNeighbour()
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
    suspend fun deleteNeighbour(neighbourEntity: NeighbourEntity): Int {
        return withContext(ioDispatcher) {
            neighbourDao.deleteNeighbour(neighbourEntity.idNeighbour)
        }
    }
}