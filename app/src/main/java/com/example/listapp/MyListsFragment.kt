package com.example.listapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.model.entity.Books
import com.example.listapp.presentation.BooksPresenterImpl
import com.example.listapp.ui.adapter.MyListAdapter

class MyListsFragment : Fragment(R.layout.fragment_my_lists), MyListAdapter.OnCategoryListClicked {
    private val LOG_TAG = "myLogs"
    private val categoryNames = arrayOf( // Засунуть в strings array
        "Непрочитанные",
        "Прочитанные",
        "Авторы",
        "Серии",
        "Аудикниги",
        "Электронные книги",
        "Не в списке"
    )
    private val recyclerViewAdapter = MyListAdapter(categoryNames, this)

    override fun onCategoryListClicked(categoryName: String) {
        if (categoryName == "Авторы") {
            val intent = Intent(activity?.applicationContext, AuthorActivity::class.java)
            intent.putExtra("books", this.arguments?.getSerializable("books"))
            activity?.startActivity(intent)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMyLists)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = recyclerViewAdapter
    }

    fun showBooks(books: List<Books>) {
        recyclerViewAdapter.setBooks(books)
    }

    fun dataUpdate() {
        recyclerViewAdapter?.notifyItemChanged(0)
        recyclerViewAdapter?.notifyItemChanged(1)
    }

    companion object {

        fun newInstance() = MyListsFragment()
    }
}
