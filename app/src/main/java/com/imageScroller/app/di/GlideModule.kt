package com.imageScroller.app.di

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.imageScroller.R

@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCache = 1024 * 1024 * 100L
        val diskCache = 1024 * 1024 * 500L

        builder.apply {
            setMemoryCache(LruResourceCache(memoryCache))
            setDiskCache(InternalCacheDiskCacheFactory(context, diskCache))
            setDefaultRequestOptions(createRequestOptions())
        }
    }

    private fun createRequestOptions(): RequestOptions {
        return RequestOptions().apply {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
            skipMemoryCache(false)
        }
    }

}