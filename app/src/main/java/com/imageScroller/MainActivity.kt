package com.imageScroller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.imageScroller.images.presentation.ImagesViewModelFactory
import dagger.android.AndroidInjection
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.imageScroller.images.presentation.ImagesAdapter
import com.imageScroller.images.presentation.ImagesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ImagesViewModelFactory
    @Inject
    lateinit var imagesAdapter: ImagesAdapter
    lateinit var viewModel: ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupViewModel()
        setupRecyclerView()
        setupSearch()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImagesViewModel::class.java)
        viewModel.imagesDataResult.observe(this, Observer { images ->
            imagesAdapter.submitList(images)
        })
    }

    private fun setupRecyclerView() {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = imagesAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchQuery = query?.toLowerCase() ?: ""
                viewModel.setQuery(searchQuery)
                viewModel.imagesDataResult.observe(this@MainActivity, Observer { images ->
                    imagesAdapter.submitList(images)
                })
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

}
