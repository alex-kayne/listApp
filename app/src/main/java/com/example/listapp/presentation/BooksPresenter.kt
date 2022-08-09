package com.example.listapp.presentation

import com.example.listapp.model.entity.Books

interface BooksPresenter {

    interface BooksListView {
        fun showBooks(books: List<Books>)
    }
}