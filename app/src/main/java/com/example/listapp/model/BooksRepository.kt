package com.example.listapp.model

import android.content.Context
import com.example.listapp.model.entity.Books
import com.example.listapp.model.source.BooksDao
import com.example.listapp.model.source.RemoteBooksSource

class BooksRepository (context: Context) {
    private val remote = RemoteBooksSource()
    private val db = BooksDao(DBHelper(context))

    fun getBooks(): List<Books> {
        val books = remote.getBooks()
        Thread.sleep(2000)
        db.deleteAllFromBooks()
        val indexes = db.insertBooks(books)
        for (i in indexes.indices) {
            books[i].dbIndex = indexes[i]
        }
        return books
    }

    fun updateRead(index: Long, read: Boolean) {
        db.updateRead(index, read)
    }
}