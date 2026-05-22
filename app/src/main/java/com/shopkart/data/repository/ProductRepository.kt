package com.shopkart.data.repository

import com.shopkart.data.local.ProductDao
import com.shopkart.data.local.ProductEntity
import com.shopkart.data.remote.ProductApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val api: ProductApi,
    private val dao: ProductDao
) {

    // 1. UI will collect this Flow. Data always comes from Room
    fun getProducts(): Flow<List<ProductEntity>> = dao.getAllProducts()

    // 2. Call this to refresh from network
    suspend fun refreshProducts() {
        try {
            val networkProducts = api.getProducts() // Call API
            val entities = networkProducts.map { dto ->
                ProductEntity(
                    id = dto.id,
                    title = dto.title,
                    price = dto.price,
                    image = dto.image
                )
            }
            dao.clearAll() // Delete old data
            dao.insertAll(entities) // Save new data
        } catch (e: Exception) {
            // Network failed. UI will still show old data from Room
            // This is "offline-first"
        }
    }
}