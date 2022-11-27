package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopDao {
    @Query("SELECT * FROM shop")
    fun getAll(): List<Shop>

    @Insert
    fun insert(shop: Shop)

    @Delete
    fun delete(shop: Shop)
}