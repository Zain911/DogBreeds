package com.example.dog_breeds.ui.favourite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dog_breeds.R
import com.example.dog_breeds.databinding.ItemDogFavouritesBinding
import com.example.dog_breeds.domain.model.DogBreed

class FavouriteAdapter(
    private var dogBreedsList: MutableList<DogBreed>,
    private val addFavouriteClick: (DogBreed) -> Unit,
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(newList: List<DogBreed>) {
        dogBreedsList = newList as MutableList<DogBreed>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDogFavouritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(var view: ItemDogFavouritesBinding) : RecyclerView.ViewHolder(view.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        favouriteButtonHandler(holder.view.addFavouriteImageView,
            dogBreedsList[position].isFavourite)
        holder.view.dogBreedNameTextView.text = dogBreedsList[position].name
        Glide
            .with(holder.view.dogCompatImageView.context)
            .load(dogBreedsList[position].dogImage)
            .into(holder.view.dogCompatImageView)


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


    }



    private fun favouriteButtonHandler(addFavouriteImageView: ImageView, isFavourite: Boolean) {
        if (isFavourite)
            addFavouriteImageView.setImageResource(R.drawable.ic_added_favorite_24)
        else
            addFavouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }

    override fun getItemCount() = dogBreedsList.size
}
