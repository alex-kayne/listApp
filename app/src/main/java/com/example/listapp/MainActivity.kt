package com.example.listapp

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val tabTitles = arrayOf("Мои книги", "Списки")
    private val books: ArrayList<Books> by lazy { generateBooks() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tl = findViewById<TabLayout>(R.id.tab_layout)
        val vp = findViewById<ViewPager2>(R.id.place_holder_vp2)
        val fragmentList =
            arrayOf(MyBooksListFragment.newInstance(books), MyListsFragment.newInstance(books))

        val adapter = VPAdapter(this, fragmentList)
        vp.adapter = adapter
        TabLayoutMediator(tl, vp) { tab, pos ->
            tab.text = tabTitles[pos]
        }.attach()
    }

    class VPAdapter(fragment: FragmentActivity, private val fragmentList: Array<Fragment>) :
        FragmentStateAdapter(fragment) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

    }

    private fun generateBooks(): ArrayList<Books> {
        val booksList = arrayListOf<Books>()
        val t = Thread {
            var dbh = DBHelper(this)
            try {
                var dbBooksArray = dbh.readAllFromBooks()
                if (dbBooksArray.size == 0) {
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
                            var name = booksArray.getJSONObject(i).getString("name")
                            var author = booksArray.getJSONObject(i).getString("author")
                            var image = booksArray.getJSONObject(i).getString("image")
                            var series = booksArray.getJSONObject(i).getBoolean("series")
                            var audiobook = booksArray.getJSONObject(i).getBoolean("audiobook")
                            var ebook = booksArray.getJSONObject(i).getBoolean("ebook")
                            var read = booksArray.getJSONObject(i).getBoolean("read")
                            var book = Books(
                                name,
                                author,
                                image,
                                series,
                                audiobook,
                                ebook,
                                dbh.insert(name, author, image, series, audiobook, ebook, read),
                                read
                            )
                            booksList.add(book)
                        }
                    }
                } else {
                    for (i in 0 until dbBooksArray.size) {
                        booksList.add(
                            Books(
                                dbBooksArray[i]["name"] as String,
                                dbBooksArray[i]["author"] as String,
                                dbBooksArray[i]["image"] as String,
                                dbBooksArray[i]["series"] == 1,
                                dbBooksArray[i]["audiobook"] == 1,
                                dbBooksArray[i]["ebook"] == 1,
                                dbBooksArray[i]["id"] as Long,
                                dbBooksArray[i]["read"] == 1
                            )
                        )
                    }
                }
            } finally {
                dbh.close()
            }
        }
        t.start()
        t.join()
        return booksList
    }
}