package com.myshopapp.testapp.arch.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductModel(
    @SerializedName("products")
    val items: List<ItemModel> = emptyList<ItemModel>()
)

@Parcelize
data class ItemModel(
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("price")
    var price: Int? = null,

    @SerializedName("discountPercentage")
    var discountPercentage: Double? = null,

    @SerializedName("rating")
    var rating: Double? = null,

    @SerializedName("stock")
    var stock: Double? = null,

    @SerializedName("brand")
    var brand: String? = null,

    @SerializedName("category")
    var category: String? = null,

    @SerializedName("thumbnail")
    var thumbnail: String? = null,

    @SerializedName("images")
    var images: List<String> = emptyList(),

    var selected: Int = 0

) : Parcelable