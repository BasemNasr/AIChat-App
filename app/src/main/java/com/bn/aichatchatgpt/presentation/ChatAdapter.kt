package com.bn.aichatchatgpt.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bn.aichatchatgpt.R
import com.bn.aichatchatgpt.data.models.MessageModel
import com.bn.aichatchatgpt.databinding.RecyclerItemTextChatMeBinding
import com.bn.aichatchatgpt.databinding.RecyclerItemTextChatOtherBinding
import java.util.*


class ChatAdapter(
    private val mContext: Context,
    private val MyUserId: Int,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var messages: ArrayList<MessageModel> = ArrayList()


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


    fun addMessages(list: ArrayList<MessageModel>) {
        messages.addAll(messages.size, list)
        notifyItemRangeInserted(messages.size, list.size)
    }

    fun getMessages(): ArrayList<MessageModel> {
        return messages
    }

    fun addMessage(message: MessageModel) {
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
        * OfferMe = 2
        * OfferOther = 3
        * */
        when (messages[position].message_type) {
            "text" -> {
                return if (messages[position].message_position == "me") {
                    //me & text
                    0
                } else {
                    //other & text
                    1
                }
            }
        }
        return 0
    }


    // text message and file name for me 0
    inner class ChatMeMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RecyclerItemTextChatMeBinding.bind(itemView)
        fun bind(item: MessageModel) = with(itemView) {
            binding.tvMeChatMessage.text = item.message
            binding.tvMeChatMessageTime.text = item.ago_time

        }

    }

    // text message and file name for other 1
    inner class ChatOtherMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RecyclerItemTextChatOtherBinding.bind(itemView)
        fun bind(item: MessageModel) = with(itemView) {
            binding.tvOtherChatMessage.text = item.message
            binding.tvOtherChatMessageTime.text = item.ago_time
        }

    }


}
