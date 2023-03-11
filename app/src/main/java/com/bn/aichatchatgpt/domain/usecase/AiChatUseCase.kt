package com.bn.aichatchatgpt.domain.usecase

import com.bn.aichatchatgpt.data.models.CompletionRequest
import com.bn.aichatchatgpt.domain.repo.ChatRepo
import javax.inject.Inject

class AiChatUseCase
@Inject
constructor(
    private val repo: ChatRepo
) {
    suspend fun sendMessage(completionRequest: CompletionRequest) =
        repo.sendMessage(completionRequest)


}