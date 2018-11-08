package com.imageScroller.app.di

import com.imageScroller.MainActivity
import com.imageScroller.images.di.ImagesProviderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ImagesProviderModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}