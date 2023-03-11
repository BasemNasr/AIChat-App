package com.bn.aichatchatgpt.presentation

import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bn.aichatchatgpt.base.ParentActivity
import com.bn.aichatchatgpt.data.models.CompletionRequest
import com.bn.aichatchatgpt.data.models.CompletionResponse
import com.bn.aichatchatgpt.data.network.remote.NetworkResponse
import com.bn.aichatchatgpt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ParentActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    private var messagesAdapter: ChatAdapter? = null
    private val mViewModel: ChatViewModel by viewModels()


    override fun initializeComponents() {
        messagesAdapter = ChatAdapter()
        setUpRecyclerView()
        observeSendMessage()

        binding.apply {
            btnSend.setOnClickListener {
                if (etTypeAMessage.text.toString().isEmpty()) {
                    etTypeAMessage.error = "required field"
                } else {
                    mViewModel.setStateEvent(
                        ChatState.SendMessage(
                            CompletionRequest(prompt = etTypeAMessage.text.toString())
                        )
                    )
                    messagesAdapter?.addMessage(
                        CompletionResponse(
                            my_message = etTypeAMessage.text.toString()
                        )
                    )
                    reachAdapterBottom()
                    etTypeAMessage.setText("")
                }
            }
        }
    }

    private fun observeSendMessage() {
        mViewModel.message.observe(this@MainActivity) {
            when (it) {
                is NetworkResponse.Success -> {
                    binding.tvTyping.visibility = View.GONE
                    messagesAdapter?.addMessage(it.body)
                    reachAdapterBottom()
                }
                is NetworkResponse.ApiError -> {
                    binding.tvTyping.visibility = View.GONE
                }
                NetworkResponse.Loading -> {
                    binding.tvTyping.visibility = View.VISIBLE
                    lifecycleScope.launch {
                        for (i in 1..50) {
                            binding.tvTyping.text = "typing ."
                            delay(500L)
                            binding.tvTyping.text = "typing .."
                            delay(500L)
                            binding.tvTyping.text = "typing ..."
                            delay(500L)
                            binding.tvTyping.text = "typing ...."
                            delay(500L)
                            binding.tvTyping.text = "typing ...."
                            binding.tvTyping.text = "typing ."
                        }
                    }
                }
                is NetworkResponse.NetworkError -> {
                    binding.tvTyping.visibility = View.GONE
                }
                is NetworkResponse.UnknownError -> {
                    binding.tvTyping.visibility = View.GONE

                }
            }
        }
    }


    private fun setUpRecyclerView() {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = false
        binding.rvMessageRecyclerview.apply {
            adapter = messagesAdapter
            layoutManager = mLayoutManager
        }

    }


    private fun reachAdapterBottom() {
        try {
            binding.rvMessageRecyclerview.scrollToPosition( 0)
        } catch (e: Exception) { e.printStackTrace() }
    }


}