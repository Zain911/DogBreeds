package com.example.dog_breeds.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dog_breeds.domain.model.DogBreed

@Dao
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDogBreed(dogBreed: DogBreed)

    @Query("DELETE From DogBreed where name =:nameDogBreed")
    fun removeDogBreed(nameDogBreed: String)

    @Query("SELECT * From DogBreed")
    fun getAllDogBreed(): LiveData<List<DogBreed>>

    @Query("SELECT * From DogBreed where name = :nameDogBreed")
    fun getSingleDogBreed(nameDogBreed: String) :DogBreed


}