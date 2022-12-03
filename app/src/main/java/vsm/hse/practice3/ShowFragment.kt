package vsm.hse.practice3

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import database.SupermarketDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show, container, false)

        val tableName = ShowFragmentArgs.fromBundle(requireArguments()).tableName
        Toast.makeText(view.context, tableName, Toast.LENGTH_SHORT).show()

        val table = view.findViewById<TableLayout>(R.id.table)


        GlobalScope.launch(Dispatchers.IO) {
            val database = SupermarketDatabase.getDatabase(view.context)
            val tableValues : MutableList<Array<String>> = mutableListOf()
            lateinit var columnNames : Array<String>

            when (tableName) {
                "Employee" -> {
                    val employeeDao = database.employeeDao()
                    val employees = employeeDao.getAll()
                    columnNames = arrayOf("id", "name", "surname", "patronymic", "position", "salary", "shop id")

                    for (employee in employees) {
                        tableValues.add(
                            arrayOf(
                                employee.employeeId.toString(),
                                employee.name,
                                employee.surname,
                                employee.patronymic,
                                employee.employeePosition,
                                employee.salary.toString(),
                                employee.shopId.toString()
                            )
                        )
                    }
                }
                "Shop" -> {
                    val shopDao = database.shopDao()
                    val shops = shopDao.getAll()
                    columnNames = arrayOf("id", "region", "city", "address")
                    
                    for (shop in shops) {
                        tableValues.add(
                            arrayOf(
                                shop.shopId.toString(),
                                shop.region,
                                shop.city,
                                shop.address
                            )
                        )
                    }
                }
                "Product" -> {
                    val productDao = database.productDao()
                    val products = productDao.getAll()
                    columnNames = arrayOf("id", "name", "category", "price", "manufacturer country")

                    for (product in products) {
                        tableValues.add(
                            arrayOf(
                                product.productId.toString(),
                                product.productName,
                                product.category,
                                product.price.toString(),
                                product.manufacturerCountry
                            )
                        )
                    }
                }
                else -> {
                    val productQuantityDao = database.productQuantityDao()
                    val productQuantities = productQuantityDao.getAll()
                    columnNames = arrayOf("shopId", "productId", "quantity")

                    for (productQuantity in productQuantities) {
                        tableValues.add(
                            arrayOf(
                                productQuantity.shopId.toString(),
                                productQuantity.productId.toString(),
                                productQuantity.quantity.toString()
                            )
                        )
                    }
                }
            }

            launch(Dispatchers.Main) {
                // add column names to table
                val columnNamesRow = view.findViewById<TableRow>(R.id.column_names)
                for (columnName in columnNames) {
                    columnNamesRow.addView(createTextView(view.context, columnName))
                }

                // add values to table
                for (row in tableValues) {
                    val tableRow = TableRow(view.context)
                    for (item in row) {
                        tableRow.addView(createTextView(view.context, item))
                    }
                    table.addView(tableRow)
                }
            }
        }

        return view
    }

    private fun createTextView(context: Context, text: String): TextView {
        val textView = TextView(context)
        textView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        textView.text = text
        textView.gravity = Gravity.CENTER
        return textView
    }

}