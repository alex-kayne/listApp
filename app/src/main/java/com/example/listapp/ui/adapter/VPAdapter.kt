package com.example.listapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.listapp.MyBooksListFragment
import com.example.listapp.MyListsFragment
import com.example.listapp.model.entity.Books
import java.io.Serializable

class VPAdapter(
    val fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    private val myBooksListFragment: MyBooksListFragment by lazy {
        fragment.supportFragmentManager.findFragmentByTag("f0") as? MyBooksListFragment
            ?: MyBooksListFragment.newInstance()
    }
    private val myListsFragment: MyListsFragment by lazy {
        fragment.supportFragmentManager.findFragmentByTag("f1") as? MyListsFragment
            ?: MyListsFragment.newInstance()
    }

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> myBooksListFragment
            else -> myListsFragment
        }
    }

    fun showBooks(books: List<Books>) {
        myListsFragment.showBooks(books)
        myBooksListFragment.showBooks(books)
    }
}