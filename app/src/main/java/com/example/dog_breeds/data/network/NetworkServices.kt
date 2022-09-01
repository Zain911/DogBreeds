package com.example.dog_breeds.data.network

import com.example.dog_breeds.data.model.dogimage.DogBreedImageResponse
import com.example.dog_breeds.data.model.dogimage.DogBreedRandomImage
import com.example.dog_breeds.data.model.dogbreed.DogBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkServices {

    @GET("breeds/list/all/")
    suspend fun getDogBreedsList(): Response<DogBreedResponse>

    @GET("breed/{breed_name}/images/")
    suspend fun getDogBreedImages(@Path("breed_name") breedName: String): Response<DogBreedImageResponse>


    @GET("breed/{breed_name}/{sub_breed_name}/images/")
    suspend fun getDogSubBreedImages(
        @Path("breed_name") breedName: String,
        @Path("sub_breed_name") subBreedName: String,
    ): Response<DogBreedImageResponse>

    @GET("breed/{breed_name}/images/random")
    suspend fun getDogBreedRandomImage(
        @Path("breed_name") breedName: String,
    ): Response<DogBreedRandomImage>
}