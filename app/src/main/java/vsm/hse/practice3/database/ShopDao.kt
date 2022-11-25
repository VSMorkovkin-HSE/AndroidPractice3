package database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface ShopDao {
    @Query("SELECT * FROM shop")
    fun getAll(): List<Shop>

    @Insert
    fun insert(shop: Shop)

    @Delete
    fun delete(shop: Shop)
}