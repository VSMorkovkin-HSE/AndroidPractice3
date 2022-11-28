package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductQuantityDao {
    @Query("SELECT * FROM productQuantity")
    fun getAll(): List<ProductQuantity>

    @Insert
    fun insert(productQuantity: ProductQuantity)

    @Delete
    fun delete(productQuantity: ProductQuantity)
}