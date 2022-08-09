package com.example.listapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.listapp.model.BooksRepository
import com.example.listapp.model.entity.Books
import com.example.listapp.ui.adapter.VPAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MyBooksListFragment.OnReadChangeListener {
    private val tabTitles = arrayOf("Мои книги", "Списки")
    private lateinit var fragmentList: Array<Fragment>
    private lateinit var adapter: FragmentStateAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var books: ArrayList<Books> = ArrayList()
    private val LOG_TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            generateBooks()
        } else {
            books = savedInstanceState.getSerializable("books") as ArrayList<Books>
            onBooksLoaded()
        }
    }



    override fun readChange() {
        (supportFragmentManager.findFragmentByTag("f1") as MyListsFragment?)?.dataUpdate()
    }

    private fun onBooksLoaded() {
        val tl = findViewById<TabLayout>(R.id.tab_layout)
        val vp = findViewById<ViewPager2>(R.id.place_holder_vp2)
        adapter = VPAdapter(this, books)
        vp.adapter = adapter
        TabLayoutMediator(tl, vp) { tab, pos ->
            tab.text = tabTitles[pos]
        }.attach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("books", books)
    }


    private fun generateBooks() {
        Thread {
            for (book in BooksRepository(this@MainActivity).getBooks() as ArrayList<Books>) {
                books.add(book)
            }
            handler.post { onBooksLoaded() }
        }.start()
    }
}