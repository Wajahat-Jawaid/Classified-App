package com.wajahat.classifiedapptest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wajahat.classifiedapptest.databinding.ListItemAdBinding
import com.wajahat.classifiedapptest.models.Ad

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
class AdsListAdapter :
    PagedListAdapter<Ad, AdsListAdapter.ViewHolder>(PhotosDiffCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        photo?.let {
            holder.apply {
//                bind(createOnClickListener(photo.id), photo)
                itemView.tag = photo
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemAdBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

//    private fun createOnClickListener(id: String): View.OnClickListener {
//        return View.OnClickListener {
//            val likesCount = (20..100).random()
//            val commentsCount = (10..65).random()
//            val direction = PhotosListFragmentDirections.actionToPhotoDetailFragment(
//                id,
//                likesCount,
//                commentsCount
//            )
//            it.findNavController().navigate(direction)
//        }
//    }

    class ViewHolder(private val binding: ListItemAdBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Ad) {
            binding.apply {
                clickListener = listener
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class PhotosDiffCallback : DiffUtil.ItemCallback<Ad>() {

    override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
        return oldItem == newItem
    }
}