package com.example.dog_breeds.data.repository.breeds

import androidx.lifecycle.LiveData
import com.example.dog_breeds.data.model.dogbreed.DogBreedResponse
import com.example.dog_breeds.domain.model.DogBreed
import retrofit2.Response

interface BreedsRepositoryInterface {
    suspend fun getDogBreedsList(): Response<DogBreedResponse>

    suspend fun getAllFavouriteBreeds(): LiveData<List<DogBreed>>

    suspend fun addBreedsToFavourite(dogBreed: DogBreed)

    suspend fun removeBreedFromFavourite(nameDogBreed: String)
    suspend fun getSingleBreedFromFavourite(dogBreedName: String): DogBreed?
}
