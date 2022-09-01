package com.example.dog_breeds.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.dog_breeds.domain.model.DogBreed
import com.example.dog_breeds.domain.usecase.FavouriteBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteBreedUseCase: FavouriteBreedUseCase,
) :
    ViewModel() {

    private val _breedsList = MutableLiveData<List<DogBreed>>()
    val breedsList: LiveData<List<DogBreed>> = _breedsList

    suspend fun favouriteClickHandler(dogBreed: DogBreed) {
        if (dogBreed.isFavourite)
            favouriteBreedUseCase.addDogBreedToFavourite(dogBreed)
        else
            favouriteBreedUseCase.removeBreedFromFavourite(dogBreed.name)
    }

    private val breedsObserver = Observer<List<DogBreed>> {
        _breedsList.postValue(it)
    }

    suspend fun getBreedList() {
        favouriteBreedUseCase.getFavouriteBreeds().observeForever(breedsObserver)
    }

}