package com.bn.aichatchatgpt.data.models

import java.io.Serializable

data class MessageModel(
    val chat_id: Int? = null,
    val id: Int? = null,
    val created_at: String? = null,
    val message: String? = null,
    val message_id: Int? = null,
    var message_position: String? = null,
    val message_sender: Int? = null,
    val message_type: String? = null,
    val order_id: Int? = null,
    val read_at: String? = null,
    val receiver_data: ReceiverData? = null,
    val sender_data: SenderData? = null,
    val sender: SenderData? = null,
    var is_seen: Boolean? = false,
    var ago_time: String? = "now",
    val is_confirmed: Boolean? = null,
    val is_feed_back_or_response: Boolean? = null,
    //val should_confirmed: Int? = null
) : Serializable {
    data class ReceiverData(
        val id: Int? = null,
        val image: String? = null,
        val name: String? = null,
        val phone: String? = null
    ): Serializable

    data class SenderData(
        val id: Int? = null,
        val image: String? = null,
        val name: String? = null,
        val fullname: String? = null,
        val phone: String? = null
    ): Serializable
}



