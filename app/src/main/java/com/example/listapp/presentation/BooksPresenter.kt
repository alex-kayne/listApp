package com.example.listapp.presentation

import com.example.listapp.model.entity.Books
import java.io.Serializable

interface BooksPresenter: Serializable {

    fun attachView(view: BooksListView)

    fun dropView()

    fun updateRead(book: Books)

    interface BooksListView {
        fun showBooks(books: List<Books>)

        fun onReadChanged()
    }
}