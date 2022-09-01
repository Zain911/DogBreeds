package com.example.dog_breeds.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DogBreed(
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "dogImage")
    var dogImage: String? = "",

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean = false,

    @Ignore
    var subBreed: List<String> = arrayListOf(),

    ) : Serializable

