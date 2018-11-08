package com.imageScroller.images.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imageScroller.images.data.ImagesDataSourceFactory

@Suppress("UNCHECKED_CAST")
class ImagesViewModelFactory(private val imagesDataSourceFactory: ImagesDataSourceFactory) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImagesViewModel(imagesDataSourceFactory) as T
    }
}