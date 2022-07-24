package com.example.connectiontest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var t = Thread {
            var url = URL("https://pastebin.com/raw/cxSZwjdV")
            var urlConnection = url.openConnection() as HttpURLConnection
            var msg: StringBuilder = java.lang.StringBuilder()
            try {
                var input = BufferedReader(InputStreamReader(urlConnection.inputStream))
                input.forEachLine {
                    Log.d(LOG_TAG, it)
                    msg.append(it)
                }
            } finally {
                urlConnection.disconnect()
                val jsonObject = JSONTokener(msg.toString()).nextValue() as JSONObject
                val booksArray = jsonObject.getJSONArray("books")
                for (i in 0..booksArray.length()) {
                    Log.d(
                        LOG_TAG,
                        booksArray.getJSONObject(i).getString("author")
                                + " "
                                + booksArray.getJSONObject(i).getString("name")
                                + " "
                                + booksArray.getJSONObject(i).getString("image")
                    )
                }
            }
        }
        t.start()

    }
}