package com.example.listapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.model.BooksRepository
import com.example.listapp.model.entity.Books
import com.example.listapp.ui.adapter.BooksAdapter


class MyBooksListFragment : Fragment(R.layout.fragment_my_books_list), BooksAdapter.OnBookClicked {
    lateinit var booksAdapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val books = arguments?.getSerializable("books") as ArrayList<Books>
        booksAdapter = BooksAdapter(books, this)
        recyclerView.adapter = booksAdapter
    }

    companion object {
        fun newInstance(books: ArrayList<Books>): MyBooksListFragment =
            MyBooksListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("books", books)
                }
            }
    }

    override fun onBookClicked(book: Books) {
        book.read = !book.read
        book.let { BooksRepository(requireContext()).updateRead(it.dbIndex, it.read) }
        (this.requireActivity() as OnReadChangeListener).readChange()
    }


    interface OnReadChangeListener {
        fun readChange()
    }
}