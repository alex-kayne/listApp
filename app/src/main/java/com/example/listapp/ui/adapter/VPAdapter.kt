package com.example.listapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.listapp.MyBooksListFragment
import com.example.listapp.MyListsFragment
import com.example.listapp.model.entity.Books

class VPAdapter(
    fragment: FragmentActivity,
    private val books: ArrayList<Books>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyBooksListFragment.newInstance(books)
            else -> MyListsFragment.newInstance(books)
        }
    }
}