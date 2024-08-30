package com.haksoftware.entrevoisins_kotlin_compose.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "neighbour")
data class NeighbourEntity(
    @PrimaryKey(autoGenerate = true)
    var idNeighbour: Int = 0,
    var userName: String,
    var description: String,
    var email: String,
    var phoneNumber: String,
    var pathPhoto: String,
    var isFavorite: Boolean,
    )
