package com.example.dog_breeds.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dog_breeds.databinding.FragmentHomeBinding
import com.example.dog_breeds.domain.model.DogBreed
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!
    private lateinit var dogsBreedsAdapter: DogsBreedsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dogsBreedsAdapter = DogsBreedsAdapter(viewModel.breedImagesUseCase, arrayListOf(), {
            val action1 = HomeFragmentDirections.actionNavHomeToDetialsFragment(it)
            findNavController().navigate(action1)
        }) {
            lifecycleScope.launch {
                viewModel.favouriteClickHandler(it)
            }
        }

        binding.articleRecyclerView.adapter = dogsBreedsAdapter
        viewModel.connectionLiveData.observe(viewLifecycleOwner) { isAvailable ->
            if (isAvailable) {
                lifecycleScope.launch {
                    viewModel.getBreedsList()
                }
            }
        }
        viewModel.breedsList.observe(viewLifecycleOwner) {

            val listOfBreeds = ArrayList<DogBreed>()
            for (dogBreed in it.message) {
                listOfBreeds.add(DogBreed(name = dogBreed.key, subBreed = dogBreed.value).also {
                    lifecycleScope.launch {
                        it.isFavourite = viewModel.favouriteBreedUseCase.isDogBreedFavourite(it.name)
                    }
                })
            }
            dogsBreedsAdapter.changeList(listOfBreeds)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}