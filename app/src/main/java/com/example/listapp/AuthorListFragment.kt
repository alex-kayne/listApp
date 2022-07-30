package com.example.listapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.ui.adapter.AuthorListAdapter

class AuthorListFragment : Fragment(R.layout.fragment_author_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.authorList)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = AuthorListAdapter(this.arguments?.getSerializable("books") as HashMap<String, ArrayList<Books>>)
    }


    companion object {
        fun newInstance(books: ArrayList<Books>) : AuthorListFragment = AuthorListFragment().apply {
            this.arguments = Bundle().apply {
                val dictAuthorBooks: HashMap<String, List<Books>> = books.groupBy { it.author } as HashMap
                putSerializable("books", dictAuthorBooks) }
        }
    }
}