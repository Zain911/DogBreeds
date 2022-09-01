package com.example.dog_breeds.data.repository.breeds

import androidx.lifecycle.LiveData
import com.example.dog_breeds.data.local.BreedsDao
import com.example.dog_breeds.data.model.dogbreed.DogBreedResponse
import com.example.dog_breeds.data.network.NetworkServices
import com.example.dog_breeds.domain.model.DogBreed
import retrofit2.Response
import javax.inject.Inject

class BreedsRepository @Inject constructor(
    private val networkServices: NetworkServices,
    private val breedsDao: BreedsDao,
) :
    BreedsRepositoryInterface {
    override suspend fun getDogBreedsList(): Response<DogBreedResponse> =
        networkServices.getDogBreedsList()

    override suspend fun getAllFavouriteBreeds(): LiveData<List<DogBreed>> = breedsDao.getAllDogBreed()


    override suspend fun addBreedsToFavourite(dogBreed: DogBreed) =
        breedsDao.addDogBreed(dogBreed)

    override suspend fun removeBreedFromFavourite(nameDogBreed: String) =
        breedsDao.removeDogBreed(nameDogBreed)

    override suspend fun getSingleBreedFromFavourite(dogBreedName : String) =
        breedsDao.getSingleDogBreed(dogBreedName)


}
