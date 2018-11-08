package com.imageScroller.images.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.imageScroller.images.domain.Image


class ImagesDataSourceFactory(val imagesDataSource: ImagesDataSource,
                              val liveData: MutableLiveData<ImagesDataSource>) : DataSource.Factory<Int, Image>() {

    override fun create(): DataSource<Int, Image> {
        liveData.postValue(imagesDataSource)
        return imagesDataSource
    }
}