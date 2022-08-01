package com.example.listapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.listapp.model.BooksRepository
import com.example.listapp.model.entity.Books
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MyBooksListFragment.OnReadChangeListener {
    private val tabTitles = arrayOf("Мои книги", "Списки")
    private lateinit var fragmentList: Array<Fragment>
    private lateinit var adapter: FragmentStateAdapter
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateBooks()
    }

    class VPAdapter(fragment: FragmentActivity, private val fragmentList: Array<Fragment>) :
        FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }

    override fun readChange(book: Books) {
        for (el in fragmentList) {
            if (el is MyListsFragment) {
                el.dataUpdate(0)
                el.dataUpdate(1)
            }
        }
    }

    private fun onBooksLoaded(books: ArrayList<Books>) {
        val tl = findViewById<TabLayout>(R.id.tab_layout)
        val vp = findViewById<ViewPager2>(R.id.place_holder_vp2)
        fragmentList =
            arrayOf(MyBooksListFragment.newInstance(books), MyListsFragment.newInstance(books))
        adapter = VPAdapter(this, fragmentList)
        vp.adapter = adapter
        TabLayoutMediator(tl, vp) { tab, pos ->
            tab.text = tabTitles[pos]
        }.attach()
    }

    private fun generateBooks() {
        val books: ArrayList<Books> = ArrayList()
        Thread {
            for (book in BooksRepository(this@MainActivity).getBooks() as ArrayList<Books>) {
                books.add(book)
            }
            handler.post { onBooksLoaded(books) }
        }.start()
    }
}