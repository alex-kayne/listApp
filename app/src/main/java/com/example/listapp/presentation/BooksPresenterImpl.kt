package com.example.listapp.presentation

import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.listapp.model.BooksRepository
import com.example.listapp.model.entity.Books
import java.io.Serializable

class BooksPresenterImpl : BooksPresenter, Serializable {
    private var books: ArrayList<Books> = ArrayList()
    private val handler = Handler(Looper.getMainLooper())
    private var view: BooksPresenter.BooksListView? = null

    fun attachView(view: BooksPresenter.BooksListView) {
        this.view = view
        onViewAttached()
    }

    fun dropView() {
        view = null
    }

    private fun onViewAttached() {
        if (books.size == 0) {
            generateBooks()
        } else {
            view?.showBooks(books)
        }
    }

    private fun generateBooks() {
        val view = this.view
        Thread {
            for (book in BooksRepository(this.view as AppCompatActivity).getBooks() as ArrayList<Books>) {
                books.add(book)
            }
            handler.post { view?.showBooks(books) }
        }.start()
    }
}