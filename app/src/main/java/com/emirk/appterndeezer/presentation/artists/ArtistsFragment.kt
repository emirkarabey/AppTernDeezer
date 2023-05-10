package com.emirk.appterndeezer.presentation.artists

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.emirk.appterndeezer.databinding.FragmentArtistsBinding
import com.emirk.appterndeezer.presentation.artists.adapter.ArtistAdapter
import com.emirk.appterndeezer.presentation.artists.adapter.ArtistClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistsFragment : Fragment() {

    private var _binding: FragmentArtistsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistsViewModel by viewModels()
    private lateinit var artistAdapter: ArtistAdapter
    private val args: ArtistsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val genreId = args.genreId
        viewModel.getArtists(genreId.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectEvent()
    }

    private fun initRecyclerViewAdapters() {
        artistAdapter = ArtistAdapter(object : ArtistClickListener {
            override fun onItemClick(artistId: Int) {
                Log.v("onitemclick", artistId.toString())
                findNavController().navigate(
                    ArtistsFragmentDirections.actionArtistsFragmentToArtistDetailFragment(
                        artistId
                    )
                )
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvArtist.layoutManager = GridLayoutManager(context, 2)
        rvArtist.adapter = artistAdapter
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        artistAdapter.submitList(uiState.artist)
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