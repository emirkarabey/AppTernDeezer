package com.emirk.appterndeezer.presentation.album_detail

import android.media.MediaPlayer
import android.os.Bundle
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
import com.emirk.appterndeezer.databinding.FragmentAlbumDetailBinding
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        val albumId = args.albumId
        viewModel.getAlbumDetail(albumId.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectEvent()
    }

    private fun initRecyclerViewAdapters() {
        albumDetailAdapter = AlbumDetailAdapter(object : AlbumDetailItemClickListener {
            override fun onItemClick(preview: String) {
                if (mediaPlayer.isPlaying && currentPlayingSong == preview) {
                    // mediaPlayer çalıyor ve tıklanan şarkı zaten çalıyor
                    mediaPlayer.pause()
                    mediaPlayer.reset()
                } else if (mediaPlayer.isPlaying && currentPlayingSong != preview) {
                    // mediaPlayer çalıyor, ama tıklanan şarkı farklı
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(preview)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    currentPlayingSong = preview
                } else {
                    // mediaPlayer çalmıyor, tıklanan şarkı çalıyor
                    try {
                        mediaPlayer.setDataSource(preview)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        currentPlayingSong = preview
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
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