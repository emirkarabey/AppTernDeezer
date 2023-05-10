package com.emirk.appterndeezer.presentation.artist_detail

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.FragmentArtistDetailBinding
import com.emirk.appterndeezer.presentation.artist_detail.adapter.ArtistAlbumAdapter
import com.emirk.appterndeezer.presentation.artist_detail.adapter.ArtistAlbumItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistDetailViewModel by viewModels()
    private lateinit var artistAlbumAdapter: ArtistAlbumAdapter
    private val args: ArtistDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        val artistId = args.artistId
        viewModel.getArtistDetail(artistId.toString())
        viewModel.getArtistAlbums(artistId.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectEventAlbums()
        collectEvent()
    }


    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (!uiState.isLoading) {
                        Glide.with(binding.ivArtistDetail)
                            .load(uiState.artistDetail?.picture_xl)
                            .into(binding.ivArtistDetail)
                    }

                    uiState.userMessage?.let {
                        binding.tvError.text = it
                        viewModel.userMessageShown()
                    }
                }
            }
        }
    }

    private fun collectEventAlbums() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiStateAlbums.collect { uiState ->
                    if (uiState.isLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        artistAlbumAdapter.submitList(uiState.artistAlbums)
                    }

                    uiState.userMessage?.let {
                        binding.tvError.text = it
                        viewModel.userMessageShownAlbums()
                    }
                }
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        artistAlbumAdapter = ArtistAlbumAdapter(object : ArtistAlbumItemClickListener {
            override fun onItemClick(albumId: Int) {
                Log.v("onitemclick", albumId.toString())
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvArtistDetail.layoutManager = GridLayoutManager(context, 1)
        rvArtistDetail.adapter = artistAlbumAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}