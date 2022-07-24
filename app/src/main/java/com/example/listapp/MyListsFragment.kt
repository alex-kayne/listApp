package com.example.listapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import java.io.Serializable

class MyListsFragment : Fragment() {
    lateinit var books: ArrayList<Books>

    private val categoryNames = arrayOf( // Засунуть в strings array
        "Непрочитанные",
        "Прочитанные",
        "Авторы",
        "Серии",
        "Аудикниги",
        "Электронные книги",
        "Не в списке"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_my_lists, container, false)
        val recyclerView = fragmentView.findViewById<RecyclerView>(R.id.rvMyLists)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MyListAdapter(categoryNames, books, activity)
        recyclerViewAdapter = recyclerView.adapter as MyListAdapter
        return fragmentView
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        var recyclerViewAdapter: MyListAdapter? = null


        @JvmStatic
        fun newInstance(books: ArrayList<Books>): MyListsFragment {
            var instance = MyListsFragment()
            instance.books = books
            return instance
        }

    }

    class MyListAdapter(private val categoryArray: Array<String>, var books: ArrayList<Books>, var activity: Context?) :
        RecyclerView.Adapter<MyListAdapter.ViewHolder>() {
        override fun getItemCount() = categoryArray.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_my_lists, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.categoryName?.text = categoryArray[position]
            holder.categoryCount?.text = 0.toString()
            holder.itemView.setOnClickListener {
                if (it.findViewById<TextView>(R.id.categoryName).text.equals("Авторы")) {
                    val intent = Intent(activity?.applicationContext, AuthorActivity::class.java)
                    intent.putExtra("books", books)
                    activity?.startActivity(intent)
                }
            }
            when (position) {
                0 -> {
                    for (book in books) {
                        if (!book.read) {
                            val imageView = ImageView(holder.itemView.context)
                            val ll = LinearLayout.LayoutParams(240, 240)
                            imageView.layoutParams = ll
                            ll.setMargins(10, 0, 0, 0)
                            Glide.with(holder.itemView.context).load(book.image).into(imageView)
                            holder.imagesLL?.addView(imageView)
                        }
                    }
                }
                1 -> {
                    for (book in books) {
                        if (book.read) {
                            val imageView = ImageView(holder.itemView.context)
                            val ll = LinearLayout.LayoutParams(240, 240)
                            imageView.layoutParams = ll
                            ll.setMargins(10, 0, 0, 0)
                            Glide.with(holder.itemView.context).load(book.image).into(imageView)
                            holder.imagesLL?.addView(imageView)
                        }
                    }
                }
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var categoryName: TextView? = itemView.findViewById(R.id.categoryName)
            var categoryCount: TextView? = itemView.findViewById(R.id.categoryCount)
            var imagesLL: LinearLayout? = itemView.findViewById(R.id.imagesLayout)
        }
    }

}
