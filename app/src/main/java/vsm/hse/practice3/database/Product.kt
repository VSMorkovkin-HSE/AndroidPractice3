package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val productId: Int, // primary key

    @ColumnInfo(name = "product_name") val productName : String,
    @ColumnInfo val category : String,
    @ColumnInfo val price : Double,
    @ColumnInfo(name = "manufacturer_country") val manufacturerCountry : String
)