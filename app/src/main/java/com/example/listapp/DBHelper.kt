package com.example.listapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "listAppDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table books (" +
                    "id integer primary key autoincrement," +
                    "name text," +
                    "image text, " +
                    "author text," +
                    "series integer," +
                    "audiobook integer," +
                    "ebook integer," +
                    "read integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insert(
        name: String,
        author: String,
        image: String,
        series: Boolean,
        audiobook: Boolean,
        ebook: Boolean,
        read: Boolean
    ): Long {
        var db = this.writableDatabase
        var cv: ContentValues = ContentValues()
        cv.put("name", name)
        cv.put("author", author)
        cv.put("image", image)
        cv.put("series", if (series) 1 else 0)
        cv.put("audiobook", if (audiobook) 1 else 0)
        cv.put("ebook", if (ebook) 1 else 0)
        cv.put("read", if (read) 1 else 0)
        db.beginTransaction()
        var id = db.insert("books", null, cv)
        db.setTransactionSuccessful()
        db.endTransaction()
        this.close()
        return id
    }




    @SuppressLint("Range")
    fun readAllFromBooks(): ArrayList<HashMap<String, Any>> {
        var db = this.writableDatabase
        var selectResult: ArrayList<HashMap<String, Any>> = ArrayList()
        var cursor: Cursor = db.query("books", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                var dict: HashMap<String, Any> = HashMap()
                dict["id"] = cursor.getLong(cursor.getColumnIndex("id"))
                dict["name"] = cursor.getString(cursor.getColumnIndex("name"))
                dict["author"] = cursor.getString(cursor.getColumnIndex("author"))
                dict["image"] = cursor.getString(cursor.getColumnIndex("image"))
                dict["series"] = cursor.getInt(cursor.getColumnIndex("series"))
                dict["audiobook"] = cursor.getInt(cursor.getColumnIndex("audiobook"))
                dict["ebook"] = cursor.getInt(cursor.getColumnIndex("ebook"))
                dict["read"] = cursor.getInt(cursor.getColumnIndex("read"))
                selectResult.add(dict)
            } while (cursor.moveToNext())
        }
        this.close()
        return selectResult
    }

    fun updateIsRead(id: Long, read: Boolean) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("read", if (read) 1 else 0)
        db.beginTransaction()
        db.update("books", cv, "id = ?", arrayOf(id.toString()))
        db.setTransactionSuccessful()
        db.endTransaction()
        this.close()
    }
}