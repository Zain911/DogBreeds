package com.example.dog_breeds.data.repository.images

import com.example.dog_breeds.data.model.dogimage.DogBreedImageResponse
import com.example.dog_breeds.data.model.dogimage.DogBreedRandomImage
import retrofit2.Response

interface BreedImagesRepositoryInterface {
    suspend fun fetchImageBreed(breedsName: String): Response<DogBreedImageResponse>

    suspend fun fetchBreedRandomImage(breedsName: String): Response<DogBreedRandomImage>
}
