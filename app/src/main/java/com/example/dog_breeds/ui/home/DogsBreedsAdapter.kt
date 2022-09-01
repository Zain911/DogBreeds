package com.example.dog_breeds.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dog_breeds.R
import com.example.dog_breeds.databinding.ItemDogBreedsBinding
import com.example.dog_breeds.domain.model.DogBreed
import com.example.dog_breeds.domain.usecase.BreedsImageUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogsBreedsAdapter(
    private val breedsImageUseCase: BreedsImageUseCase,
    private var dogBreedsList: MutableList<DogBreed>,
    private val navigateToDetailsScreen: (DogBreed) -> Unit,
    private val addFavouriteClick: (DogBreed) -> Unit,
) : RecyclerView.Adapter<DogsBreedsAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(newList: List<DogBreed>) {
        dogBreedsList = newList as MutableList<DogBreed>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDogBreedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(var view: ItemDogBreedsBinding) : RecyclerView.ViewHolder(view.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        favouriteButtonHandler(holder.view.addFavouriteImageView,
            dogBreedsList[position].isFavourite)
        holder.view.dogBreedNameTextView.text = dogBreedsList[position].name
        holder.view.subBreedsNumberValueTextView.text =
            dogBreedsList[position].subBreed.size.toString()

        if (dogBreedsList[position].dogImage.isNullOrEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {

                val response = breedsImageUseCase.getBreedImages(dogBreedsList[position].name)

                if (response.isSuccessful) {
                    val urlDogImage = response.body()?.message?.get(0)
                    dogBreedsList[position].dogImage = urlDogImage
                    loadBreedImage(holder.view.dogCompatImageView, urlDogImage)
                }
            }
        } else {

            loadBreedImage(holder.view.dogCompatImageView, dogBreedsList[position].dogImage)
        }

        holder.view.addFavouriteImageView.setOnClickListener {

            if (dogBreedsList[position].isFavourite) {
                addFavouriteClick(dogBreedsList[position].apply {
                    this.isFavourite = false
                })
            } else {
                addFavouriteClick(dogBreedsList[position].apply {
                    this.isFavourite = true
                })
            }
            favouriteButtonHandler(holder.view.addFavouriteImageView,
                dogBreedsList[position].isFavourite)
        }
        holder.view.mainContainerConstraintLayout.setOnClickListener {
            navigateToDetailsScreen(dogBreedsList[position])
        }

    }

    private fun loadBreedImage(dogCompatImageView: AppCompatImageView, imageDogUrl: String?) {
        Glide
            .with(dogCompatImageView.context)
            .load(imageDogUrl)
            .into(dogCompatImageView)
    }

    private fun favouriteButtonHandler(addFavouriteImageView: ImageView, isFavourite: Boolean) {
        if (isFavourite)
            addFavouriteImageView.setImageResource(R.drawable.ic_added_favorite_24)
        else
            addFavouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }

    override fun getItemCount() = dogBreedsList.size
}
