package com.example.listapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.listapp.model.entity.Books
import com.example.listapp.presentation.BooksPresenter
import com.example.listapp.ui.adapter.VPAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), MyBooksListFragment.OnReadChangeListener,
    BooksPresenter.BooksListView {
    private val tabTitles = arrayOf("Мои книги", "Списки")
    private val adapter = VPAdapter(this)
    private val presenter: BooksPresenter by lazy {
        component().providePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        val tl = findViewById<TabLayout>(R.id.tab_layout)
        val vp = findViewById<ViewPager2>(R.id.place_holder_vp2)
        vp.adapter = adapter
        TabLayoutMediator(tl, vp) { tab, pos ->
            tab.text = tabTitles[pos]
        }.attach()
    }

    override fun readChange(books: Books) {
        presenter.updateRead(books)
    }

    override fun onReadChanged() {
        (supportFragmentManager.findFragmentByTag("f1") as MyListsFragment?)?.dataUpdate()
    }

    override fun showBooks(books: List<Books>) {
        adapter.showBooks(books)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

}