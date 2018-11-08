package com.imageScroller.images.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.imageScroller.api.ImagesService
import com.imageScroller.images.domain.Image
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImagesDataSource @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                           private val imagesService: ImagesService) : PageKeyedDataSource<Int, Image>() {


    var query: String = ""

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Image>) {
        val disposable = imagesService.getImages(query, 1, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    callback.onResult(result.assets, null, 2)
                }, { error -> /*TODO*/ })
        compositeDisposable.add(disposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        val disposable = imagesService.getImages(query, params.key, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    callback.onResult(result.assets, params.key + 1)
                }, { error -> /*TODO*/ })
        compositeDisposable.add(disposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
    }

    fun clear() {
        compositeDisposable.clear()
    }
}

const val PAGE_SIZE = 40
val pagedListConfig = PagedList.Config.Builder().apply {
    setEnablePlaceholders(false)
    setInitialLoadSizeHint(PAGE_SIZE)
    setPageSize(PAGE_SIZE)
}.build()
