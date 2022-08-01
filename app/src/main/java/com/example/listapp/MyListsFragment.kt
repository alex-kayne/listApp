package com.example.listapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.model.entity.Books
import com.example.listapp.ui.adapter.MyListAdapter

class MyListsFragment : Fragment(R.layout.fragment_my_lists), MyListAdapter.OnCategoryListClicked {
    var recyclerViewAdapter: MyListAdapter? = null


    private val categoryNames = arrayOf( // Засунуть в strings array
        "Непрочитанные",
        "Прочитанные",
        "Авторы",
        "Серии",
        "Аудикниги",
        "Электронные книги",
        "Не в списке"
    )

    override fun onCategoryListClicked(categoryName: String) {
        if (categoryName.equals("Авторы")) {
            val intent = Intent(activity?.applicationContext, AuthorActivity::class.java)
            intent.putExtra("books", this.arguments?.getSerializable("books"))
            activity?.startActivity(intent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMyLists)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter = MyListAdapter(
            categoryNames,
            arguments?.getSerializable("books") as ArrayList<Books>,
            this
        )
        recyclerView.adapter = recyclerViewAdapter
    }

    fun dataUpdate(pos: Int) {
        recyclerViewAdapter?.notifyItemChanged(pos)
    }

    companion object {

        fun newInstance(books: ArrayList<Books>) = MyListsFragment().apply {
            arguments = Bundle().apply { putSerializable("books", books) }
        }
    }
}
