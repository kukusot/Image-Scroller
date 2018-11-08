package com.imageScroller.images.domain

import com.google.gson.annotations.SerializedName

data class PageResponse(val page: Int,
                        val perPage: Int,
                        val totalCount: Int,
                        val searchId: String,
                        @SerializedName("data") val assets: MutableList<Image>)