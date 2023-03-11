package com.bn.aichatchatgpt.di

import com.bn.aichatchatgpt.data.repoImp.ChatRepoImp
import com.bn.aichatchatgpt.domain.repo.ChatRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindChatRepository(chatRepoImp: ChatRepoImp): ChatRepo
}