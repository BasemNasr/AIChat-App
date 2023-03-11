package com.bn.aichatchatgpt.data.network

import com.bn.aichatchatgpt.data.models.BaseResponse
import com.bn.aichatchatgpt.data.models.CompletionRequest
import com.bn.aichatchatgpt.data.network.remote.NetworkResponse
import retrofit2.http.*

@JvmSuppressWildcards
interface ServiceApi {

    /**
     * @author General
     * */

    @POST(Urls.COMPLETIONS)
    suspend fun completions(
        @Header("Authorization") auth: String?,
        @Body completionRequest: CompletionRequest
    ): NetworkResponse<BaseResponse, BaseResponse>

}