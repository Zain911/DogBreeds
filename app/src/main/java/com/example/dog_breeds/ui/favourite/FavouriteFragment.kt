package com.example.dog_breeds.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dog_breeds.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null

    private val viewModel: FavouriteViewModel by viewModels()

    private val binding get() = _binding!!
    private lateinit var favouriteAdapter: FavouriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favouriteAdapter = FavouriteAdapter(arrayListOf()) {
            lifecycleScope.launch {
                viewModel.favouriteClickHandler(it)
            }
        }
        lifecycleScope.launch {
            viewModel.getBreedList()
        }
        binding.articleRecyclerView.adapter = favouriteAdapter

        viewModel.breedsList.observe(viewLifecycleOwner) {
            favouriteAdapter.changeList(it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}