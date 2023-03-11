package com.bn.aichatchatgpt.domain.repo

import com.bn.aichatchatgpt.data.models.BaseResponse
import com.bn.aichatchatgpt.data.models.CompletionRequest
import com.bn.aichatchatgpt.data.models.CompletionResponse
import com.bn.aichatchatgpt.data.network.remote.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepo {
    suspend fun sendMessage(completionRequest: CompletionRequest):
            Flow<NetworkResponse<CompletionResponse, BaseResponse>>
}