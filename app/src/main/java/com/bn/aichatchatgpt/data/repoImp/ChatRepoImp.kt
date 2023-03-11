package com.bn.aichatchatgpt.data.repoImp


import com.bn.aichatchatgpt.BuildConfig
import com.bn.aichatchatgpt.data.models.BaseResponse
import com.bn.aichatchatgpt.data.models.CompletionRequest
import com.bn.aichatchatgpt.data.network.ServiceApi
import com.bn.aichatchatgpt.data.network.remote.NetworkResponse
import com.bn.aichatchatgpt.domain.repo.ChatRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatRepoImp @Inject constructor(
    private val retrofit: ServiceApi,
) : ChatRepo {

    override suspend fun sendMessage(completionRequest: CompletionRequest) = flow {
        emit(NetworkResponse.Loading)
        when (val get = retrofit.completions("Bearer ${BuildConfig.CHAT_GPT_KEY}", completionRequest)) {
            is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
            is NetworkResponse.ApiError -> if (get.code == 500) emit(
                NetworkResponse.ApiError(
                    BaseResponse(code = 500, message = "Internal Server Error"), get.code
                )
            )
            else emit(NetworkResponse.ApiError(get.body, get.code))
            is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
            is NetworkResponse.UnknownError -> emit(NetworkResponse.UnknownError(get.error))
            else -> emit(NetworkResponse.Loading)
        }
    }


}