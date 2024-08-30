package com.haksoftware.entrevoisins_kotlin_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haksoftware.entrevoisins_kotlin_compose.data.entity.NeighbourEntity
import com.haksoftware.entrevoisins_kotlin_compose.data.repository.NeighboursRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NeighboursViewModel @Inject constructor(
    private val neighboursRepository: NeighboursRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _neighboursList = MutableStateFlow<List<NeighbourEntity>>(emptyList())
    val neighboursList: StateFlow<List<NeighbourEntity>> get() = _neighboursList

    private val _favoritesNeighboursList = MutableStateFlow<List<NeighbourEntity>>(emptyList())
    val favoritesNeighboursList: StateFlow<List<NeighbourEntity>> get() = _favoritesNeighboursList

    init {
        loadNeighbours()
        loadFavoriteNeighbours()
    }

    private fun loadFavoriteNeighbours() {
        viewModelScope.launch(ioDispatcher) {
            neighboursRepository.getAllFavoritesNeighbourFlow().collect {
                    favoritesNeighboursList ->
                _favoritesNeighboursList.value = favoritesNeighboursList
            }

        }
    }

    private fun loadNeighbours() {
        viewModelScope.launch(ioDispatcher) {
            neighboursRepository.getAllNeighbourFlow().collect {
                neighboursList ->
                _neighboursList.value = neighboursList
            }

        }
    }

    suspend fun getNeighbour(neighbourId: Int): NeighbourEntity {
        return withContext(ioDispatcher) { neighboursRepository.getNeighbour(neighbourId) }
    }

    fun insertNeighbour(neighbour: NeighbourEntity, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                neighboursRepository.insertNeighbour(neighbour)
                loadNeighbours()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun updateNeighbour(neighbour: NeighbourEntity, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                neighboursRepository.updateNeighbour(neighbour)
                loadNeighbours()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
    fun deleteNeighbour(neighbourId: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                neighboursRepository.deleteNeighbour(neighbourId)
                loadNeighbours()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}

