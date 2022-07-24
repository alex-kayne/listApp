package com.example.listapp

import java.io.Serializable

class Books(
    var name: String,
    var author: String,
    var image: String,
    var series: Boolean,
    var audiobook: Boolean,
    var ebook: Boolean,
    var dbIndex: Long,
    var read: Boolean
): Serializable {

    init {
        var outOfLists: Boolean = !series && !audiobook && !ebook
    }
}