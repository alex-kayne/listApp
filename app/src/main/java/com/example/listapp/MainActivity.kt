package com.example.listapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.listapp.model.entity.Books
import com.example.listapp.presentation.BooksPresenter
import com.example.listapp.presentation.BooksPresenterImpl
import com.example.listapp.ui.adapter.VPAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MyBooksListFragment.OnReadChangeListener,
    BooksPresenter.BooksListView {
    private val tabTitles = arrayOf("Мои книги", "Списки")
    private val adapter = VPAdapter(this)
    private var presenter: BooksPresenterImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = if (savedInstanceState == null) {
            BooksPresenterImpl()
        } else {
            (savedInstanceState.getSerializable("presenter") as BooksPresenterImpl)
        }
        presenter?.attachView(this)
        val tl = findViewById<TabLayout>(R.id.tab_layout)
        val vp = findViewById<ViewPager2>(R.id.place_holder_vp2)
        vp.adapter = adapter
        TabLayoutMediator(tl, vp) { tab, pos ->
            tab.text = tabTitles[pos]
        }.attach()
    }

    override fun readChange() {
        (supportFragmentManager.findFragmentByTag("f1") as MyListsFragment?)?.dataUpdate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("presenter", presenter)
    }

    override fun showBooks(books: List<Books>) {
        adapter.showBooks(books)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.dropView()
    }
}