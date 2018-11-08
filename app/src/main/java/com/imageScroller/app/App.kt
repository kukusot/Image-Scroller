package com.imageScroller.app

import com.imageScroller.app.di.DaggerAppComponent
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder().app(this).build()

    override fun applicationInjector() = appComponent
}