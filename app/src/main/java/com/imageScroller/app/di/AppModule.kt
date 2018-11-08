package com.imageScroller.app.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
object AppModule {

    @Provides
    @JvmStatic
    fun provideDisposable() = CompositeDisposable()
}