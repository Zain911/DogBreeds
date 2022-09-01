package com.example.dog_breeds.domain.usecase

import com.example.dog_breeds.data.repository.images.BreedImagesRepositoryInterface
import javax.inject.Inject


class BreedsImageUseCase @Inject constructor(private val imageBreeds: BreedImagesRepositoryInterface) {

    suspend fun getBreedImages(breedName: String) = imageBreeds.fetchImageBreed(breedName)


}
