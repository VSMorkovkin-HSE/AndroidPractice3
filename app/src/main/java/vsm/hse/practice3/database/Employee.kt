package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(
    entity = Shop::class,
    parentColumns = arrayOf(""),
    childColumns = arrayOf("")
)))
data class Employee(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employee_id")
    val employeeId: Int, // primary key

    @ColumnInfo val name: String,
    @ColumnInfo val surname: String,
    @ColumnInfo val patronymic: String,
    @ColumnInfo(name = "employee_position") val employeePosition: String,
    @ColumnInfo val salary: Double,

    @ColumnInfo(name = "shop_id") val shopId: Int // foreign key
)