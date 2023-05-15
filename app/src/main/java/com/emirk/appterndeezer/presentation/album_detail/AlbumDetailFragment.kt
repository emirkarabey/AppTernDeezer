package com.emirk.appterndeezer.presentation.album_detail

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.emirk.appterndeezer.databinding.FragmentAlbumDetailBinding
import com.emirk.appterndeezer.domain.ui_model.Track
import com.emirk.appterndeezer.presentation.album_detail.adapter.AlbumDetailAdapter
import com.emirk.appterndeezer.presentation.album_detail.adapter.AlbumDetailItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {

    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumDetailViewModel by viewModels()
    private lateinit var albumDetailAdapter: AlbumDetailAdapter
    private val args: AlbumDetailFragmentArgs by navArgs()
    val mediaPlayer = MediaPlayer()
    var currentPlayingSong: String? = null
    var albumId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        viewModel.getFavorite()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectEvent()
        albumId = args.albumId
        viewModel.getAlbumDetail(albumId.toString())
    }

    private fun initRecyclerViewAdapters() {
        albumDetailAdapter = AlbumDetailAdapter(object : AlbumDetailItemClickListener {
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
                    Toast.makeText(requireContext(),"Şuan da çalan şarkı: ${track.title}",Toast.LENGTH_SHORT).show()
                } else {
                    // mediaPlayer çalmıyor, tıklanan şarkı çalıyor
                    try {
                        mediaPlayer.setDataSource(track.preview)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        currentPlayingSong = track.preview
                        Toast.makeText(requireContext(),"Şuan da çalan şarkı: ${track.title}",Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFavItemClick(track: Track, position: Int) {
                if (track.isFav == true) {
                    track.isFav = false
                    viewModel.deleteFavorite(track)
                    albumDetailAdapter.notifyItemChanged(position)
                } else {
                    track.isFav = true
                    viewModel.addFavorite(track)
                    albumDetailAdapter.notifyItemChanged(position)
                    Log.v("dataUiState", "add ${track.title}")
                }

            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvAlbum.layoutManager = GridLayoutManager(context, 1)
        rvAlbum.adapter = albumDetailAdapter
    }

    private fun collectEvent() = binding.apply {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    if (uiState.isLoading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.INVISIBLE
                        val albumName = args.albumName
                        binding.tvPageName.text = albumName
                        binding.tvPageName.visibility = View.VISIBLE
                        albumDetailAdapter.submitList(uiState.albumSong)
                    }

                    uiState.userMessage?.let {
                        binding.tvError.text = it
                        viewModel.userMessageShown()
                    }
                }
            }
        }
    }
}