package com.example.dog_breeds.domain.usecase

import com.example.dog_breeds.data.repository.breeds.BreedsRepositoryInterface
import javax.inject.Inject


class BreedsListUseCase @Inject constructor(private val breedsRepo: BreedsRepositoryInterface) {

    suspend fun getDogBreedsList() =
        breedsRepo.getDogBreedsList()

}
