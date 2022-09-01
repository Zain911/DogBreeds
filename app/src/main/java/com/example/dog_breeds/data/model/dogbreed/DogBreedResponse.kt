package com.example.dog_breeds.data.model.dogbreed

data class DogBreedResponse(
    val status: String,
    val message: Map<String, List<String>>,
)

