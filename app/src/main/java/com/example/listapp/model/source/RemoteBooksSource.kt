package com.example.listapp.model.source

import com.example.listapp.model.entity.Books
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RemoteBooksSource {
    fun getBooks(): List<Books> {
        val books: ArrayList<Books> = ArrayList()
        val url = URL("https://pastebin.com/raw/cxSZwjdV")
        val urlConnection = url.openConnection() as HttpURLConnection
        val msg: StringBuilder = java.lang.StringBuilder()
        try {
            val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
            input.forEachLine {
                msg.append(it)
            }
        } finally {
            urlConnection.disconnect()
            val jsonObject = JSONTokener(msg.toString()).nextValue() as JSONObject
            val booksArray = jsonObject.getJSONArray("books")
            for (i in 0 until booksArray.length()) {
                val name = booksArray.getJSONObject(i).getString("name")
                val author = booksArray.getJSONObject(i).getString("author")
                val image = booksArray.getJSONObject(i).getString("image")
                val series = booksArray.getJSONObject(i).getBoolean("series")
                val audiobook = booksArray.getJSONObject(i).getBoolean("audiobook")
                val ebook = booksArray.getJSONObject(i).getBoolean("ebook")
                val read = booksArray.getJSONObject(i).getBoolean("read")
                val book = Books(
                    name,
                    author,
                    image,
                    series,
                    audiobook,
                    ebook,
                    0,
                    read
                )
                books.add(book)
            }
        }
        return books
    }
}
