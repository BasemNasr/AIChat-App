package com.bn.aichatchatgpt.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bn.aichatchatgpt.R
import com.bn.aichatchatgpt.data.models.CompletionResponse
import com.bn.aichatchatgpt.databinding.RecyclerItemTextChatMeBinding
import com.bn.aichatchatgpt.databinding.RecyclerItemTextChatOtherBinding
import java.text.SimpleDateFormat
import java.util.*


class ChatAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var messages: ArrayList<CompletionResponse> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> {
                //text,file and me
                val myMessageView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item_text_chat_me, parent, false)
                ChatMeMessageViewHolder(myMessageView)
            }
            1 -> {
                //text, file  and other
                val otherMessageView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item_text_chat_other, parent, false)
                ChatOtherMessageViewHolder(otherMessageView)
            }

            else -> {
                val myMessageView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_item_text_chat_me, parent, false)
                ChatMeMessageViewHolder(myMessageView)
            }
        }
    }


    fun addMessages(list: ArrayList<CompletionResponse>) {
        messages.addAll(messages.size, list)
        notifyItemRangeInserted(messages.size, list.size)
    }

    fun getMessages(): ArrayList<CompletionResponse> {
        return messages
    }

    fun addMessage(message: CompletionResponse) {
        messages.add(0, message)
        notifyItemInserted(0)
        notifyItemRangeChanged(0, messages.size, messages)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is ChatMeMessageViewHolder -> {
                holder.bind(messages[position])
            }
            is ChatOtherMessageViewHolder -> {
                holder.bind(messages[position])
            }
        }


    }

    override fun getItemCount(): Int {
        return messages.size
    }


    fun clearAllMessages() {
        if (messages.isNotEmpty()) {
            val size = messages.size
            messages.clear()
            notifyItemRangeRemoved(0, size)
        }
    }


    override fun getItemViewType(position: Int): Int {
        /*
        * TextMe = 0
        * TextOther = 1
        */
        if (messages[position].my_message != null) {
            return 0
        } else {
            return 1
        }
        return 0
    }


    // text message and file name for me 0
    inner class ChatMeMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RecyclerItemTextChatMeBinding.bind(itemView)
        fun bind(item: CompletionResponse) = with(itemView) {
            binding.tvMeChatMessage.text = item.my_message
            binding.tvMeChatMessageTime.text = "${getCurrentTime()}"

        }

    }

    // text message and file name for other 1
    inner class ChatOtherMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RecyclerItemTextChatOtherBinding.bind(itemView)
        fun bind(item: CompletionResponse) = with(itemView) {
            if (item.choices?.isNotEmpty() == true) {
                binding.tvOtherChatMessage.text = "${item.choices[0]?.text}"
            } else {
                binding.tvOtherChatMessage.text = "No Response"
            }
            binding.tvOtherChatMessageTime.text = "${getCurrentTime()}"
        }

    }

    private fun getCurrentTime():String{
        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    }


}
