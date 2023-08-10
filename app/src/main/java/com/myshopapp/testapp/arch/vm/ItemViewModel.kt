package com.myshopapp.testapp.arch.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myshopapp.testapp.arch.entities.ItemModel
import com.myshopapp.testapp.arch.entities.ProductModel
import com.myshopapp.testapp.arch.repos.ItemRepoPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepo: ItemRepoPattern
) : ViewModel() {

    val resultFlow = MutableSharedFlow<Result<String>>()

    val shareMutableLiveData =
        MutableLiveData<com.myshopapp.testapp.arch.repos.Result<ProductModel>>()

    fun fetchItems() {
        viewModelScope.launch {
            shareMutableLiveData.postValue(itemRepo.fetchItems())
        }
    }

    fun fetchCartItems() {

    }

    fun order(list: ArrayList<ItemModel>?) {
        viewModelScope.launch {
//            resultFlow.emit(itemRepo.order())
        }
    }
}



