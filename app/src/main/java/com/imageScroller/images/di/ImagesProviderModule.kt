package com.imageScroller.images.di

import androidx.lifecycle.MutableLiveData
import com.imageScroller.app.di.ActivityScope
import com.imageScroller.images.data.ImagesDataSource
import com.imageScroller.images.data.ImagesDataSourceFactory
import com.imageScroller.images.presentation.ImagesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ImagesProviderModule {


    @ActivityScope
    @Provides
    fun provideImagesDataSourceFactory(imagesDataSource: ImagesDataSource, liveData: MutableLiveData<ImagesDataSource>) = ImagesDataSourceFactory(imagesDataSource, liveData)

    @ActivityScope
    @Provides
    fun provideImageSourceLiveData() = MutableLiveData<ImagesDataSource>()

    @ActivityScope
    @Provides
    fun provideImagesViewModelFactory(imagesDataSourceFactory: ImagesDataSourceFactory) = ImagesViewModelFactory(imagesDataSourceFactory)

}