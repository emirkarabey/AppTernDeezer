package com.emirk.appterndeezer.di

import com.emirk.appterndeezer.data.repository.*
import com.emirk.appterndeezer.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun genresRepository(GenresRepositoryImpl: GenresRepositoryImpl): GenresRepository

    @Binds
    @Singleton
    abstract fun artistRepository(ArtistRepositoryImpl: ArtistRepositoryImpl): ArtistRepository

    @Binds
    @Singleton
    abstract fun artistDetailRepository(ArtistDetailRepositoryImpl: ArtistDetailRepositoryImpl): ArtistDetailRepository

    @Binds
    @Singleton
    abstract fun artistAlbumsRepository(ArtistAlbumsRepositoryImpl: ArtistAlbumsRepositoryImpl): ArtistAlbumsRepository

    @Binds
    @Singleton
    abstract fun albumDetailRepository(AlbumDetailRepositoryImpl: AlbumDetailRepositoryImpl): AlbumDetailRepository

    @Binds
    @Singleton
    abstract fun favoriteRepository(FavoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}