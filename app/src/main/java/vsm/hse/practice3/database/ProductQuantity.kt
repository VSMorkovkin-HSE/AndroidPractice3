package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = arrayOf("shop_id", "product_id"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Shop::class,
            childColumns = arrayOf("shop_id"),
            parentColumns = arrayOf("shop_id")
        ),
        ForeignKey(
            entity = Product::class,
            childColumns = arrayOf("product_id"),
            parentColumns = arrayOf("product_id")
        )
    ),
    tableName = "productQuantity"
)
data class ProductQuantity (
    // primary keys
    @ColumnInfo(name = "shop_id") val shopId : Int,
    @ColumnInfo(name = "product_id") val productId : Int,

    @ColumnInfo val quantity : Int,
    // @ColumnInfo(name = "cost_of_all") val costOfAll : Double
)