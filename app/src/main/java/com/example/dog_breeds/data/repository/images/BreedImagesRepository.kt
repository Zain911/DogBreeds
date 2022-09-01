package com.example.dog_breeds.data.repository.images

import com.example.dog_breeds.data.model.dogimage.DogBreedRandomImage
import com.example.dog_breeds.data.network.NetworkServices
import retrofit2.Response
import javax.inject.Inject

class BreedImagesRepository @Inject constructor(private val networkServices: NetworkServices) :
    BreedImagesRepositoryInterface {


    override suspend fun fetchImageBreed(breedsName: String) =
        networkServices.getDogBreedImages(breedsName)

    override suspend fun fetchBreedRandomImage(breedsName: String): Response<DogBreedRandomImage> =
        networkServices.getDogBreedRandomImage(breedsName)


}
