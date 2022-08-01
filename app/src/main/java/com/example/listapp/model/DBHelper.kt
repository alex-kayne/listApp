package com.example.listapp.model

import android.content.Context
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


}