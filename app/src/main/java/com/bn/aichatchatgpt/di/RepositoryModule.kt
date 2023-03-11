package com.bn.aichatchatgpt.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
//
//    @Binds
//    @ViewModelScoped
//    abstract fun bindPlacesRepository(placesRepositoryImpl: PlacesRepositoryImpl): PlacesRepository
}