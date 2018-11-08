package com.imageScroller.images.presentation

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imageScroller.R
import com.imageScroller.app.di.GlideApp
import com.imageScroller.images.domain.Image
import com.imageScroller.utils.getLayoutInflater
import javax.inject.Inject
import kotlinx.android.synthetic.main.item_image.view.*


class ImagesAdapter @Inject constructor() : PagedListAdapter<Image, ImagesAdapter.ImageViewHolder>(ImagesDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = parent.context.getLayoutInflater().inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }


    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(image: Image?, position: Int) {
            if (image != null) {
                GlideApp.with(itemView)
                        .load(image.assets.hugeThumb.url)
                        .into(itemView.imageView)
            }
            itemView.textView.text = position.toString()
        }
    }
}


object ImagesDiffCallback : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
}