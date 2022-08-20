package com.example.listapp.presentation

import android.os.Handler
import android.os.Looper
import com.example.listapp.model.BooksRepository
import com.example.listapp.model.entity.Books

class BooksPresenterImpl (private val booksRepository: BooksRepository) : BooksPresenter {
    private var books: ArrayList<Books> = ArrayList()
    private val handler = Handler(Looper.getMainLooper())
    private var view: BooksPresenter.BooksListView? = null


    override fun attachView(view: BooksPresenter.BooksListView) {
        this.view = view
        onViewAttached()
    }

    override fun dropView() {
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
            for (book in booksRepository.getBooks() as ArrayList<Books>) {
                books.add(book)
            }
            handler.post { view?.showBooks(books) }
        }.start()
    }

    override fun updateRead(book: Books) {
        booksRepository.updateRead(book.dbIndex, book.read)
        view?.onReadChanged()
    }

}