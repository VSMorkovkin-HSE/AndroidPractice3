package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Shop (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shop_id")
    val shopId: Int, // primary key

    @ColumnInfo val region: String,
    @ColumnInfo val city: String,
    @ColumnInfo val address : String
)

