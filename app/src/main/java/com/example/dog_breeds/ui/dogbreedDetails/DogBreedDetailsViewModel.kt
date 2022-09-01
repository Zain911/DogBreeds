package com.example.dog_breeds.ui.dogbreedDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dog_breeds.domain.usecase.BreedsImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(private val imagesUseCase: BreedsImageUseCase) :
    ViewModel() {

    private val _imagesList = MutableLiveData<List<String>>()
    val imagesList: LiveData<List<String>> = _imagesList

    suspend fun getBreedImages(breedName: String) {
        _imagesList.postValue(imagesUseCase.getBreedImages(breedName).body()?.message)
    }


}