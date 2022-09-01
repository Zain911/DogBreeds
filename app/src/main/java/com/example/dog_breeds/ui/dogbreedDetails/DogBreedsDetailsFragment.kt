package com.example.dog_breeds.ui.dogbreedDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.dog_breeds.databinding.FragmentDogBreedDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogBreedsDetailsFragment : Fragment() {

    private var _binding: FragmentDogBreedDetailsBinding? = null

    private val viewModel: DogBreedDetailsViewModel by viewModels()

    private val args: DogBreedsDetailsFragmentArgs by navArgs()

    private val binding get() = _binding!!

    private lateinit var imagesAdapter: BreedImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDogBreedDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val breed = args.dogBreed

        binding.breedNameTextView.text = breed.name

        binding.subBreedsRecyclerView.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, breed.subBreed)

        imagesAdapter = BreedImagesAdapter(arrayListOf())

        binding.breedImagesRecyclerView.adapter = imagesAdapter

        lifecycleScope.launch {
            viewModel.getBreedImages(breed.name)
        }

        viewModel.imagesList.observe(viewLifecycleOwner) {
            imagesAdapter.changeList(it as MutableList<String>)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}