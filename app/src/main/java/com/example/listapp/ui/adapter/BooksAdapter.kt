package com.example.listapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listapp.Books
import com.example.listapp.DBHelper
import com.example.listapp.R
import com.example.listapp.ui.util.DoubleClickListener

class BooksAdapter(
    private val values: ArrayList<Books>,
    private val bookClicked: OnBookClicked
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_my_books, parent, false)
        val vH = BookViewHolder(itemView)
        itemView.setOnClickListener(DoubleClickListener { v ->
            val book = values[vH.adapterPosition]
            v.isSelected = !book.read
            bookClicked.onBookClicked(book)
        })
        return vH
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(values[position])
    }

    class BookViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val ivBookCover: ImageView = itemView.findViewById(R.id.bookCover)
        private val tvBookName: TextView = itemView.findViewById(R.id.bookName)
        private val tvAuthorName: TextView = itemView.findViewById(R.id.authorName)

        fun bind(book: Books) {
            tvBookName.text = book.name
            tvAuthorName.text = book.author
            Glide.with(itemView.context)
                .load(book.image)
                .into(ivBookCover)
            itemView.isSelected = book.read
        }
    }

    interface OnBookClicked {
        fun onBookClicked(book: Books)
    }
}