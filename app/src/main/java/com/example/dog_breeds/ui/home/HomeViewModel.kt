package com.example.dog_breeds.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dog_breeds.data.model.dogbreed.DogBreedResponse
import com.example.dog_breeds.domain.model.DogBreed
import com.example.dog_breeds.domain.usecase.BreedsImageUseCase
import com.example.dog_breeds.domain.usecase.BreedsListUseCase
import com.example.dog_breeds.domain.usecase.FavouriteBreedUseCase
import com.example.dog_breeds.domain.util.InternetConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val breedsListUseCase: BreedsListUseCase,
    val breedImagesUseCase: BreedsImageUseCase,
    val favouriteBreedUseCase: FavouriteBreedUseCase,
    @ApplicationContext context: Context,
) :
    ViewModel() {

    private val _breedsList = MutableLiveData<DogBreedResponse>()
    val breedsList: LiveData<DogBreedResponse> = _breedsList

    suspend fun getBreedsList() =
        _breedsList.postValue(breedsListUseCase.getDogBreedsList().body())

    val connectionLiveData = InternetConnectivity(context)

    suspend fun favouriteClickHandler(dogBreed: DogBreed) {
        if (dogBreed.isFavourite)
            favouriteBreedUseCase.addDogBreedToFavourite(dogBreed)
        else
            favouriteBreedUseCase.removeBreedFromFavourite(dogBreed.name)
    }

}