package com.example.listapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listapp.Books
import com.example.listapp.DBHelper
import com.example.listapp.MyListsFragment
import com.example.listapp.R

class BooksAdapter(
    private val values: ArrayList<Books>
) :
    RecyclerView.Adapter<BooksAdapter.ViewHolder>(), View.OnClickListener {
    val LOG_TAG = "myLogs"
    var lastClickTime: Long = 0
    private var books: HashMap<View, Books>? = HashMap()

    override fun getItemCount() = values.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context) // from вызывает вопрос
            .inflate(R.layout.item_list_my_books, parent, false)
        itemView.setOnClickListener(this)
        return ViewHolder(itemView)
    }

    override fun onClick(v: View) {
        var clickTime = System.currentTimeMillis()
        if (clickTime - lastClickTime < 500) {
            lastClickTime = 0
            Log.d(LOG_TAG, books?.get(v)?.read.toString())
            books?.get(v)?.read = books?.get(v)?.read != true
            v.isSelected = books?.get(v)!!.read
            var dbh = DBHelper(v.context!!)
            books?.get(v)?.let { dbh.updateIsRead(it.dbIndex, it.read) }
            MyListsFragment.recyclerViewAdapter?.notifyItemChanged(0)
            MyListsFragment.recyclerViewAdapter?.notifyItemChanged(1)
            return
        }
        lastClickTime = clickTime
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(values[position].image)
            .into(holder.ivBookCover)
        holder.tvBookName.text = values[position].name
        holder.tvAuthorName.text = values[position].author
        books?.put(holder.itemView, values[position])
        holder.itemView.isSelected = books?.get(holder.itemView)!!.read
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var ivBookCover: ImageView = itemView.findViewById(R.id.bookCover)
        var tvBookName: TextView = itemView.findViewById(R.id.bookName)
        var tvAuthorName: TextView = itemView.findViewById(R.id.authorName)
    }
}