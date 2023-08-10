package com.myshopapp.testapp.arch.retro

import com.myshopapp.testapp.arch.entities.ProductModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface RetrofitInterface {

    @GET("https://dummyjson.com/products")
    fun fetchItems(): Deferred<Response<ProductModel>>

    @POST("")
    fun order(): Response<String>
}