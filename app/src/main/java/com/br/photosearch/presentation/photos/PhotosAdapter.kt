package com.br.photosearch.presentation.photos

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.br.photosearch.R
import com.br.photosearch.domain.models.Photo
import com.br.photosearch.domain.models.large
import com.br.photosearch.domain.models.thumbnail
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_photo.view.*

class PhotosAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Photo>() {

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_photo,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotosViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Photo>) {
        differ.submitList(list)
    }

    class PhotosViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Photo) = with(itemView) {
            photoClick.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item, thumbnail, title)
            }
            title.text = item.title

            Glide.with(thumbnail.context)
                .load(item.large())
                .placeholder(R.drawable.ic_photo_size_select_actual_black_24dp)
                .error(R.drawable.ic_photo_size_select_actual_black_24dp)
                .into(thumbnail)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Photo, imageView: ImageView, textView: TextView)
    }
}