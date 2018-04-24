package com.example.data.network.api.response

import com.google.gson.annotations.SerializedName

data class Meta(
        @SerializedName("page")
        val page: Int,
        @SerializedName("has_next")
        val hasNext: Boolean,
        @SerializedName("has_previous")
        val hasPrevious: Boolean
)