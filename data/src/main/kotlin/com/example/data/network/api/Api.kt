package com.example.data.network.api

import com.example.data.data.BuildConfig

fun searchCourses(
    query: String = "",
    page: Int = 1,
    is_popular: Boolean = true,
    is_public: Boolean = true,
    language: String = "en",
    type: String = "course",
    server: String = BuildConfig.SERVER
) = "$server/api/search-results?query=$query&page=$page&is_popular=$is_popular&is_public=$is_public&language=$language&type=$type"