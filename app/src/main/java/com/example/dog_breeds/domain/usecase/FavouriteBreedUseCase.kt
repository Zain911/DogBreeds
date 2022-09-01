package com.example.dog_breeds.domain.usecase

import com.example.dog_breeds.data.repository.breeds.BreedsRepositoryInterface
import com.example.dog_breeds.domain.model.DogBreed
import javax.inject.Inject


class FavouriteBreedUseCase @Inject constructor(private val breedsRepo: BreedsRepositoryInterface) {

    suspend fun addDogBreedToFavourite(breed: DogBreed) =
        breedsRepo.addBreedsToFavourite(dogBreed = breed)

    suspend fun getFavouriteBreeds() =
        breedsRepo.getAllFavouriteBreeds()

    suspend fun removeBreedFromFavourite(nameDogBreed: String) =
        breedsRepo.removeBreedFromFavourite(nameDogBreed)

    suspend fun isDogBreedFavourite(dogBreedName: String): Boolean {
        val response = breedsRepo.getSingleBreedFromFavourite(dogBreedName)
        return response != null
    }

}
