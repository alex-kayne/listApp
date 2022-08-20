package com.example.listapp.model.source

import com.example.listapp.model.dto.BooksDTO
import com.example.listapp.model.entity.Books
import com.example.listapp.model.retrofit.RetrofitServices
import com.google.gson.Gson

class RemoteBooksSource(private val service: RetrofitServices) {

//    fun getBooks(): List<Books> = service.getBooksList().execute().body()?.books ?: emptyList()

    fun getBooks(): List<Books> = Gson().fromJson(booksJson, BooksDTO::class.java).books

    val booksJson: String = "{\n" +
            "    \"books\": [{\n" +
            "            \"name\": \"Книга 1\",\n" +
            "            \"author\": \"Автор 1\",\n" +
            "            \"image\": \"https://mobimg.b-cdn.net/v3/fetch/9d/9db2d4683d92f5f2045e9142fbd82633.jpeg\",\n" +
            "\t\t\t\"series\": true,\n" +
            "\t\t\t\"audiobook\": true,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": true\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 9\",\n" +
            "            \"author\": \"Автор 1\",\n" +
            "            \"image\": \"https://mobimg.b-cdn.net/v3/fetch/9d/9db2d4683d92f5f2045e9142fbd82633.jpeg\",\n" +
            "\t\t\t\"series\": true,\n" +
            "\t\t\t\"audiobook\": true,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": false\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 2\",\n" +
            "            \"author\": \"Автор 2\",\n" +
            "            \"image\": \"https://klike.net/uploads/posts/2019-05/1556708032_1.jpg\",\n" +
            "\t\t\t\"series\": true,\n" +
            "\t\t\t\"audiobook\": true,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": false\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 3\",\n" +
            "            \"author\": \"Автор 3\",\n" +
            "            \"image\": \"https://mobimg.b-cdn.net/v3/fetch/24/246ef72c76c9d998a0005f1321a38c60.jpeg\",\n" +
            "\t\t\t\"series\": true,\n" +
            "\t\t\t\"audiobook\": true,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": false\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 4\",\n" +
            "            \"author\": \"Автор 4\",\n" +
            "            \"image\": \"https://vjoy.cc/wp-content/uploads/2020/10/dlya_dushi_35_13130628.jpg\",\n" +
            "\t\t\t\"series\": false,\n" +
            "\t\t\t\"audiobook\": true,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": false\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 5\",\n" +
            "            \"author\": \"Автор 5\",\n" +
            "            \"image\": \"https://avatarko.ru/img/kartinka/33/multfilm_lyagushka_32117.jpg\",\n" +
            "\t\t\t\"series\": false,\n" +
            "\t\t\t\"audiobook\": true,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": false\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 6\",\n" +
            "            \"author\": \"Автор 6\",\n" +
            "            \"image\": \"https://cs8.pikabu.ru/post_img/big/2016/02/04/7/145458292112119207.jpg\",\n" +
            "\t\t\t\"series\": false,\n" +
            "\t\t\t\"audiobook\": false,\n" +
            "\t\t\t\"ebook\": true,\n" +
            "\t\t\t\"read\": false\n" +
            "        }, {\n" +
            "            \"name\": \"Книга 7\",\n" +
            "            \"author\": \"Автор 7\",\n" +
            "            \"image\": \"https://ic.pics.livejournal.com/humus/758313/33171645/33171645_original.jpg\",\n" +
            "\t\t\t\"series\": false,\n" +
            "\t\t\t\"audiobook\": false,\n" +
            "\t\t\t\"ebook\": false,\n" +
            "\t\t\t\"read\": false\n" +
            "        }\n" +
            "    ]\n" +
            "}"
}