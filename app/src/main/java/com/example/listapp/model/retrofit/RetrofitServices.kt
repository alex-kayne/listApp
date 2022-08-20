package com.example.listapp.model.retrofit

import com.example.listapp.model.dto.BooksDTO
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("cxSZwjdV")
    fun getBooksList(): Call<BooksDTO>
}