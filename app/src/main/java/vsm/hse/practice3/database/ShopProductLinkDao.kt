package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopProductLinkDao {
    @Query("SELECT * FROM shopProductLink")
    fun getAll(): List<ShopProductLink>

    @Insert
    fun insert(shopProductLink: ShopProductLink)

    @Delete
    fun delete(shopProductLink: ShopProductLink)
}