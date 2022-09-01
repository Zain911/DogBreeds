package com.example.dog_breeds.ui.dogbreedDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dog_breeds.databinding.ItemBreedImageBinding

class BreedImagesAdapter(private var imagesList: MutableList<String>) :
    RecyclerView.Adapter<BreedImagesAdapter.ViewHolder>() {

    class ViewHolder(var view: ItemBreedImageBinding) : RecyclerView.ViewHolder(view.root)

    fun changeList(newImageList: MutableList<String>) {
        imagesList.clear()
        imagesList = newImageList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBreedImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.view.breedImageView.context)
            .load(imagesList[position])
            .into(holder.view.breedImageView)
    }

    override fun getItemCount() = imagesList.size
}