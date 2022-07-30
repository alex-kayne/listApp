package com.example.listapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.Books
import com.example.listapp.R


class AuthorListAdapter(
    private val dictAuthorBooks: HashMap<String, ArrayList<Books>>
) : RecyclerView.Adapter<AuthorListAdapter.ViewHolder>() {

    override fun getItemCount() = dictAuthorBooks.keys.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_author_lists, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            dictAuthorBooks.keys.elementAt(position),
            dictAuthorBooks.values.elementAt(position).size,
            dictAuthorBooks.values.elementAt(position).map { it.image })
    }

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private var authorName: TextView = itemView.findViewById(R.id.authorListAuthorName)
        private var booksCount: TextView = itemView.findViewById(R.id.booksCount)
        private var imagesRV: RecyclerView = itemView.findViewById(R.id.rvImagesAuthorlist)
        private var adapter = RvImagesAdapter()

        init {
            imagesRV.adapter = adapter
            imagesRV.layoutManager =
                LinearLayoutManager(
                    itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }

        fun bind(name: String, count: Int, covers: List<String>) {
            this.authorName.text = name
            this.booksCount.text = count.toString()
            adapter.setCovers(covers)
        }
    }
}