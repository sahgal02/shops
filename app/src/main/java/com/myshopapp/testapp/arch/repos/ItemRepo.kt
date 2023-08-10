package com.myshopapp.testapp.arch.repos

import androidx.lifecycle.ViewModel
import com.myshopapp.testapp.arch.entities.ItemModel
import com.myshopapp.testapp.arch.entities.ProductModel
import com.myshopapp.testapp.arch.retro.RetrofitUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Singleton

class ItemRepo @Inject constructor() : ItemRepoPattern {
    override suspend fun fetchItems(): Result<ProductModel> {
        val response = RetrofitUtils.callRetrofit().fetchItems().await()
        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else com.myshopapp.testapp.arch.repos.Result.Failure(
            response.code(),
            Exception(response.message())
        )
    }

    override suspend fun fetchCartItems(): List<ItemModel> {
        TODO("Not yet implemented")
    }

    override suspend fun order(): Result<String> {
        TODO("Not yet implemented")
    }


}

sealed class Result<out T> {
    data class Success<R>(val data: R) : Result<R>()
    data class Failure(val errorCode: Int, val exception: Throwable) : Result<Nothing>()
}