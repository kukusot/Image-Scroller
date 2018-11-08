package com.imageScroller.images.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.imageScroller.images.data.ImagesDataSourceFactory
import com.imageScroller.images.data.pagedListConfig
import com.imageScroller.images.domain.Image
import javax.inject.Inject

class ImagesViewModel @Inject constructor(val imagesDataSourceFactory: ImagesDataSourceFactory) : ViewModel() {

    var imagesDataResult: LiveData<PagedList<Image>> = LivePagedListBuilder(imagesDataSourceFactory, pagedListConfig).build()

    override fun onCleared() {
        super.onCleared()
        imagesDataSourceFactory.imagesDataSource.clear()
    }

    fun setQuery(query: String) {
        imagesDataSourceFactory.imagesDataSource.query = query
        imagesDataResult = LivePagedListBuilder(imagesDataSourceFactory, pagedListConfig).build()
    }

}