package com.shopkart.data

import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.shopkart.data.repository.ProductRepository
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            // 1. Fetch from network and save to DB
            repository.refreshProducts()

            // 2. Read from DB and log
            repository.getProducts().collect { products ->
                Log.d("DBTest", "Total products in Room: ${products.size}")
                products.forEach {
                    Log.d("DBTest", "${it.id}: ${it.title}")
                }
            }
        }
    }
}