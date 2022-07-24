package com.example.glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "myLogs"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iv = findViewById<ImageView>(R.id.iamgeView)

        var url = URL("https://pastebin.com/raw/cxSZwjdV")
        var urlConnection = url.openConnection() as HttpURLConnection
        try {
            var input = BufferedInputStream(urlConnection.inputStream)
            Log.d(LOG_TAG, input.toString())
        } finally {
            urlConnection.disconnect()
        }
    }
}