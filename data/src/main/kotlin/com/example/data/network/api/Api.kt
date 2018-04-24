package com.example.data.network.api

import com.example.data.model.dto.CourseMainDataDto
import com.example.data.network.api.response.ApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    
    @GET("/search-results")
    fun searchCourses(
            @Query("query") query: String = "",
            @Query("page") page: Int = 1,
            @Query("is_popular") is_popular: Boolean = true,
            @Query("is_public") is_public: Boolean = true,
            @Query("language") language: String = "en",
            @Query("type") type: String = "course"): Single<ApiResponse<List<CourseMainDataDto>>>

}