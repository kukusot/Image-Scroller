package com.imageScroller.api

import com.imageScroller.images.domain.PageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesService {

    @GET("images/search")
    fun getImages(@Query("query") query: String,
                  @Query("page") page: Int,
                  @Query("per_page") perPage: Int = 2): Single<PageResponse>
}