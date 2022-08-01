package com.example.listapp.model.source

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.example.listapp.model.DBHelper
import com.example.listapp.model.entity.Books

class BooksDao(private val dbh: DBHelper) {


    fun insertBooks(books: List<Books>): List<Long> {
        val listIndex: ArrayList<Long> = ArrayList()
        val db = dbh.writableDatabase
        db.beginTransaction()
        val cv: ContentValues = ContentValues()
        books.forEach {
            cv.put("name", it.name)
            cv.put("author", it.author)
            cv.put("image", it.image)
            cv.put("series", it.series)
            cv.put("audiobook", it.audiobook)
            cv.put("ebook", it.ebook)
            cv.put("read", it.read)
            listIndex.add(db.insert("books", null, cv))
        }
        db.setTransactionSuccessful()
        db.endTransaction()
        return listIndex
    }

    @SuppressLint("Range")
    fun getBooks(): List<Books> {
        val db = dbh.writableDatabase
        val books: ArrayList<Books> = ArrayList()
        val cursor: Cursor = db.query("books", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                books.add(
                    Books(
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("author")),
                        cursor.getString(cursor.getColumnIndex("image")),
                        cursor.getInt(cursor.getColumnIndex("series")) == 1,
                        cursor.getInt(cursor.getColumnIndex("audiobook")) == 1,
                        cursor.getInt(cursor.getColumnIndex("ebook")) == 1,
                        cursor.getLong(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("read")) == 1
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return books
    }


    fun updateRead(id: Long, read: Boolean) {
        val db = dbh.writableDatabase
        val cv = ContentValues()
        cv.put("read", if (read) 1 else 0)
        db.beginTransaction()
        db.update("books", cv, "id = ?", arrayOf(id.toString()))
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    fun deleteAllFromBooks() {
        val db = dbh.writableDatabase
        db.beginTransaction()
        db.delete("books", null, null)
        db.setTransactionSuccessful()
        db.endTransaction()
    }
}
