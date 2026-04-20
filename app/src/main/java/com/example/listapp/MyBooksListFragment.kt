package com.example.listapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapp.model.entity.Books
import com.example.listapp.ui.adapter.BooksAdapter


class MyBooksListFragment : Fragment(R.layout.fragment_my_books_list), BooksAdapter.OnBookClicked {
    private val booksAdapter = BooksAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = booksAdapter
    }

    fun showBooks(books: List<Books>) {
        booksAdapter.setBooks(books)
    }

    companion object {
        fun newInstance(): MyBooksListFragment = MyBooksListFragment()
    }

    override fun onBookClicked(book: Books) {
        book.read = !book.read
        (this.requireActivity() as OnReadChangeListener).readChange(book)
    }

    interface OnReadChangeListener {
        fun readChange(books: Books)
    }
}