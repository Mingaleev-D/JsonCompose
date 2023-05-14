package com.example.jsoncompose.data.model


import com.google.gson.annotations.SerializedName

data class JsonResponseItem(
    @SerializedName("answer")
    val answer: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("choices")
    val choices: List<String>,
    @SerializedName("question")
    val question: String
)