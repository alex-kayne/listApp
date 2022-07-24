package com.example.listapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AuthorListFragment : Fragment() {
    lateinit var books: ArrayList<Books>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_author_list, container, false)
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.authorList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MyAuthorListAdapter(books)
        return fragmentView
    }

    companion object {
        private var dictAuthorBooks: HashMap<String, ArrayList<Books>> = HashMap()
        @JvmStatic
        fun newInstance(books: ArrayList<Books>): AuthorListFragment {
            val instance = AuthorListFragment()
            instance.books = books
            for (book in books) {
                if (dictAuthorBooks.containsKey(book.author)) {
                    dictAuthorBooks[book.author]?.add(book)
                } else {
                    dictAuthorBooks[book.author] = arrayListOf(book)
                }
            }
            return instance
        }

        class MyAuthorListAdapter(var books: ArrayList<Books>) :
            RecyclerView.Adapter<MyAuthorListAdapter.ViewHolder>() {
            private val LOG_TAG = "myLogs"

            override fun getItemCount() = dictAuthorBooks.keys.size

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_author_lists, parent, false)
                return ViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.authorName?.text = dictAuthorBooks.keys.elementAt(position)
                holder.booksCount?.text =
                  dictAuthorBooks[dictAuthorBooks.keys.elementAt(position)]?.size.toString()
                for (book in dictAuthorBooks[dictAuthorBooks.keys.elementAt(position)]!!) {
                    val imageView = ImageView(holder.itemView.context)
                    val ll = LinearLayout.LayoutParams(240, 240)
                    imageView.layoutParams = ll
                    ll.setMargins(10, 0, 0, 0)
                    Glide.with(holder.itemView.context).load(book.image).into(imageView)
                    holder.imagesLL?.addView(imageView)
                }
            }

            class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                var authorName: TextView? = itemView.findViewById(R.id.authorListauthorName)
                var booksCount: TextView? = itemView.findViewById(R.id.booksCount)
                var imagesLL: LinearLayout? = itemView.findViewById(R.id.authorBooksImagesLayout)
            }
        }
    }
}