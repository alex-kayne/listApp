package com.example.listapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyBooksListFragment : Fragment() {
    lateinit var books: ArrayList<Books>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_my_books_list, container, false)
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = BooksAdapter(books)
        return fragmentView
    }

    companion object {
        @JvmStatic
        fun newInstance(books: ArrayList<Books>): MyBooksListFragment {
            var res = MyBooksListFragment()
            res.books = books
            return res
        }
    }


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
                if (books?.get(v)?.read == true) v.setBackgroundColor(Color.parseColor("green"))
                else v.setBackgroundColor(Color.parseColor("white"))
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
            if (values[position].read) holder.itemView.setBackgroundColor(Color.parseColor("green"))
            else holder.itemView.setBackgroundColor(Color.parseColor("white"))
        }

        class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            var ivBookCover: ImageView = itemView.findViewById(R.id.bookCover)
            var tvBookName: TextView = itemView.findViewById(R.id.bookName)
            var tvAuthorName: TextView = itemView.findViewById(R.id.authorName)
        }
    }
}