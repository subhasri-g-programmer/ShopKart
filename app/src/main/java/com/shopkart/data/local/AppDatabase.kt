package com.shopkart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1) // We'll add ProductEntity Day 2
abstract class AppDatabase : RoomDatabase()