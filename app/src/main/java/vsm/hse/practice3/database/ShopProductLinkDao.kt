package database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface ShopProductLinkDao {
    @Query("SELECT * FROM shopproductlink")
    fun getAll(): List<ShopProductLink>

    @Insert
    fun insert(shopProductLink: ShopProductLink)

    @Delete
    fun delete(shopProductLink: ShopProductLink)
}