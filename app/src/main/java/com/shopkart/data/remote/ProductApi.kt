package com.shopkart.data.remote

import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}


data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String
)