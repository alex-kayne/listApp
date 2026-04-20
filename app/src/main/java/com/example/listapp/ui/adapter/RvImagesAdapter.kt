package com.example.listapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listapp.R

class RvImagesAdapter() : RecyclerView.Adapter<RvImagesAdapter.ViewHolder>() {
    private var covers: List<String> = ArrayList()

    override fun getItemCount(): Int = covers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_rv_images, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(covers[position])
    }

    fun setCovers(covers: List<String>) {
        this.covers = covers
        notifyDataSetChanged()
    }

    class ViewHolder(private val imageView: View) : RecyclerView.ViewHolder(imageView) {

        fun bind(link: String) {
            Glide.with(itemView.context).load(link).into(imageView as ImageView)
        }
    }
}