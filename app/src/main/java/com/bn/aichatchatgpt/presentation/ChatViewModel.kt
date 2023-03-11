package com.bn.aichatchatgpt.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bn.aichatchatgpt.data.models.BaseResponse
import com.bn.aichatchatgpt.data.models.CompletionRequest
import com.bn.aichatchatgpt.data.models.CompletionResponse
import com.bn.aichatchatgpt.data.network.remote.NetworkResponse
import com.bn.aichatchatgpt.domain.usecase.AiChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatUseCase: AiChatUseCase,
) : ViewModel() {
    private lateinit var launchIn: Job

    private val _message: MutableLiveData<NetworkResponse<CompletionResponse, BaseResponse>> =
        MutableLiveData()
    val message: LiveData<NetworkResponse<CompletionResponse, BaseResponse>>
        get() = _message

    fun setStateEvent(type: ChatState) {
        viewModelScope.launch {
            when (type) {
                is ChatState.SendMessage -> {
                    launchIn = chatUseCase.sendMessage(type.completionRequest)
                        .onEach { _message.value = it }.launchIn(viewModelScope)
                }

            }
        }

    }
}

sealed class ChatState {
    class SendMessage(
        val completionRequest: CompletionRequest
    ) : ChatState()
}