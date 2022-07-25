package com.example.listapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.ui.adapter.MyListAdapter

class MyListsFragment : Fragment(R.layout.fragment_my_lists) {

    private val categoryNames = arrayOf( // Засунуть в strings array
        "Непрочитанные",
        "Прочитанные",
        "Авторы",
        "Серии",
        "Аудикниги",
        "Электронные книги",
        "Не в списке"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMyLists)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = MyListAdapter(
            categoryNames,
            arguments?.getSerializable("books") as ArrayList<Books>,
            activity
        )
        recyclerViewAdapter = recyclerView.adapter as MyListAdapter
    }


    companion object {
        @SuppressLint("StaticFieldLeak")
        var recyclerViewAdapter: MyListAdapter? = null

        @JvmStatic
        fun newInstance(books: ArrayList<Books>) = MyListsFragment().apply {
            arguments = Bundle().apply { putSerializable("books", books) }
        }
    }
}
