package com.example.dog_breeds.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dog_breeds.domain.model.DogBreed


@Database(entities = [DogBreed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDog(): BreedsDao
}