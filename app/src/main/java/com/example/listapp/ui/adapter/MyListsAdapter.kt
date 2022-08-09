package com.example.listapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.model.entity.Books
import com.example.listapp.R
import java.io.Serializable

class MyListAdapter(
    private val categoryArray: Array<String>,
    var books: ArrayList<Books>,
    var categoryClicked: OnCategoryListClicked
) :
    RecyclerView.Adapter<MyListAdapter.ViewHolder>(),
    Serializable{
    override fun getItemCount() = categoryArray.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_my_lists, parent, false)
        val vh = ViewHolder(itemView)
        itemView.setOnClickListener {
            categoryClicked.onCategoryListClicked(categoryArray[vh.adapterPosition])
        }
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val covers = books.filter { ((position == 0 && !it.read) || (position == 1 && it.read)) }.map { it.image }
        holder.bind(categoryArray[position], covers.size, covers)
    }

    interface OnCategoryListClicked {
        fun onCategoryListClicked(categoryName: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        private val categoryCount: TextView = itemView.findViewById(R.id.categoryCount)
        private var imagesRV: RecyclerView = itemView.findViewById(R.id.rvImages)
        private var adapter: RvImagesAdapter = RvImagesAdapter()

        init {
            imagesRV.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            imagesRV.adapter = adapter
        }

        fun bind(name: String, count: Int, covers: List<String>) {
            categoryName.text = name
            categoryCount.text = count.toString()
            adapter.setCovers(covers)
        }
    }
}