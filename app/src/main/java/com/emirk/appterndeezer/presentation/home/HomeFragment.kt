package com.emirk.appterndeezer.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.emirk.appterndeezer.databinding.FragmentHomeBinding
import com.emirk.appterndeezer.domain.ui_model.Genre
import com.emirk.appterndeezer.presentation.home.adapter.GenresAdapter
import com.emirk.appterndeezer.presentation.home.adapter.GenresClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        viewModel.getCategories()
        collectEvent()
    }

    private fun initRecyclerViewAdapters() {
        genresAdapter = GenresAdapter(object : GenresClickListener {
            override fun onItemClick(genre: Genre) {
                Log.v("onitemclick", genre.toString())
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToArtistsFragment2(
                        genreId = genre.id, genreName = genre.name
                    )
                )
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvCategory.layoutManager = GridLayoutManager(context, 2)
        rvCategory.adapter = genresAdapter
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        binding.tvPageName.visibility = View.VISIBLE
                        genresAdapter.submitList(uiState.genre)
                    }

                    uiState.userMessage?.let {
                        binding.tvError.text = it
                        viewModel.userMessageShown()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}