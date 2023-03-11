package com.bn.aichatchatgpt.data.models

data class CompletionResponse(
    val choices: List<Choice?>? = null,
    val created: Int? = null,
    val id: String? = null,
    val my_message: String? = null,
    val model: String? = null,
    val `object`: String? = null,
    val usage: Usage? = null
) {
    data class Choice(
        val finish_reason: String? = null,
        val index: Int? = null,
        val logprobs: Any? = null,
        val text: String? = null
    )

    data class Usage(
        val completion_tokens: Int? = null,
        val prompt_tokens: Int? = null,
        val total_tokens: Int? = null
    )
}