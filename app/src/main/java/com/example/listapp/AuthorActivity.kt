package com.example.listapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class AuthorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)

        val books = intent.getSerializableExtra("books") as ArrayList<Books>


        var authorFrag = AuthorListFragment.newInstance(books)
        var fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.add(R.id.author_place_holder_vp2, authorFrag)
        fragTrans.commit()
    }
}