package com.myshopapp.testapp.arch.repos

import androidx.lifecycle.ViewModel
import com.myshopapp.testapp.arch.entities.ItemModel
import com.myshopapp.testapp.arch.entities.ProductModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ItemRepoPattern {

    suspend fun fetchItems() : Result<ProductModel>

    suspend fun fetchCartItems() : List<ItemModel>

    suspend fun order() : Result<String>
}