package com.imageScroller.images.domain

data class Image(val id: Long,
                 val aspect: Float,
                 val assets: Assets,
                 val description: String,
                 val imageType: String,
                 val mediaType: String)