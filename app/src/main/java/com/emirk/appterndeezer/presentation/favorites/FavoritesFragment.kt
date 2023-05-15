package com.emirk.appterndeezer.presentation.favorites

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.emirk.appterndeezer.databinding.FragmentFavoritesBinding
import com.emirk.appterndeezer.domain.ui_model.Track
import com.emirk.appterndeezer.presentation.favorites.adapter.FavoritesAdapter
import com.emirk.appterndeezer.presentation.favorites.adapter.FavoritesItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter
    val mediaPlayer = MediaPlayer()
    var currentPlayingSong: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavorites()
        initRecyclerViewAdapters()
        collectEvent()
    }

    private fun initRecyclerViewAdapters() {
        favoritesAdapter = FavoritesAdapter(object : FavoritesItemClickListener {
            override fun onItemClick(track: Track) {
                if (mediaPlayer.isPlaying && currentPlayingSong == track.preview) {
                    // mediaPlayer çalıyor ve tıklanan şarkı zaten çalıyor
                    mediaPlayer.pause()
                    mediaPlayer.reset()
                } else if (mediaPlayer.isPlaying && currentPlayingSong != track.preview) {
                    // mediaPlayer çalıyor, ama tıklanan şarkı farklı
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(track.preview)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    currentPlayingSong = track.preview
                    Toast.makeText(requireContext(),"Şuanda çalan şarkı: ${track.title}",Toast.LENGTH_SHORT).show()
                } else {
                    // mediaPlayer çalmıyor, tıklanan şarkı çalıyor
                    try {
                        mediaPlayer.setDataSource(track.preview)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        currentPlayingSong = track.preview
                        Toast.makeText(requireContext(),"Şuanda çalan şarkı: ${track.title}",
                            Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFavItemClick(track: Track, position: Int) {
                viewModel.deleteFavorite(track)
                favoritesAdapter.notifyItemChanged(position)
                viewModel.getFavorites()
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvFavorite.layoutManager = GridLayoutManager(context, 1)
        rvFavorite.adapter = favoritesAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        if (uiState.albumSong.isNullOrEmpty()) {
                            binding.linearData.visibility = View.GONE
                            binding.linearError.visibility = View.VISIBLE
                            progressBar.visibility = View.INVISIBLE
                            binding.tvPageName.visibility = View.VISIBLE
                        } else {
                            binding.linearData.visibility = View.VISIBLE
                            binding.linearError.visibility = View.GONE
                            progressBar.visibility = View.INVISIBLE
                            binding.tvPageName.visibility = View.VISIBLE
                            favoritesAdapter.submitList(uiState.albumSong)
                        }
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