package com.imageScroller.images.domain

data class Assets(val preview: Asset,
                  val smallThumb: Asset,
                  val largeThumb: Asset,
                  val hugeThumb: Asset)