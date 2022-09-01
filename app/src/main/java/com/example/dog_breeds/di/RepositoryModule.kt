package com.example.dog_breeds.di


import com.example.dog_breeds.data.local.BreedsDao
import com.example.dog_breeds.data.repository.breeds.BreedsRepository
import com.example.dog_breeds.data.network.NetworkServices
import com.example.dog_breeds.data.repository.breeds.BreedsRepositoryInterface
import com.example.dog_breeds.data.repository.images.BreedImagesRepository
import com.example.dog_breeds.data.repository.images.BreedImagesRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBreedsRepository(
        networkServices: NetworkServices,
        breedsDao: BreedsDao,
    ): BreedsRepositoryInterface {
        return BreedsRepository(networkServices, breedsDao)
    }

    @Singleton
    @Provides
    fun provideBreedsImagesRepository(networkServices: NetworkServices): BreedImagesRepositoryInterface {
        return BreedImagesRepository(networkServices)
    }


}