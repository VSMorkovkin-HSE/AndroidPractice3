package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Employee::class,
        Shop::class,
        ShopProductLink::class,
        Product::class],
    version = 1
)
abstract class SupermarketDatabase: RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    abstract fun shopDao(): ShopDao
    abstract fun shopProductLinkDao(): ShopProductLinkDao
    abstract fun productDao(): ProductDao

    companion object {
        private var instance: SupermarketDatabase? = null

        fun getDatabase(context: Context): SupermarketDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        SupermarketDatabase::class.java,
                        "SupermarketDatabase"
                    ).build()
                }
            }
            return instance!!
        }
    }
}