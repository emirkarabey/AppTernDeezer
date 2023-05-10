package com.emirk.appterndeezer.di

import com.emirk.appterndeezer.data.repository.ArtistRepositoryImpl
import com.emirk.appterndeezer.data.repository.CategoryRepositoryImpl
import com.emirk.appterndeezer.domain.repository.ArtistRepository
import com.emirk.appterndeezer.domain.repository.CategoryRepository
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
    abstract fun categoryRepository(CategoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun artistRepository(ArtistRepositoryImpl: ArtistRepositoryImpl): ArtistRepository
}