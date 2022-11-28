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

        val idTextView = view.findViewById<TextView>(R.id.id)
        val params = idTextView.layoutParams
        val table = view.findViewById<TableLayout>(R.id.table)

        when(tableName) {
            "Employee" -> {
                val columnNames = arrayOf("id", "name", "surname", "patronymic", "position", "salary", "shop id")
                addColumnNamesToTable(table, columnNames, view)

                GlobalScope.launch(Dispatchers.IO) {
                    val database = SupermarketDatabase.getDatabase(view.context)
                    val employeeDao = database.employeeDao()
                    val employees = employeeDao.getAll()

                    launch(Dispatchers.Main) {
                        for (employee in employees) {
                            val tableRow = TableRow(view.context)
                            val textViews = arrayOf(
                                createTextView(view.context, params, employee.employeeId.toString()),
                                createTextView(view.context, params, employee.name),
                                createTextView(view.context, params, employee.surname),
                                createTextView(view.context, params, employee.patronymic),
                                createTextView(view.context, params, employee.employeePosition),
                                createTextView(view.context, params, employee.salary.toString()),
                                createTextView(view.context, params, employee.shopId.toString())
                            )
                            addTextViewsToTableRow(tableRow, textViews)

                            table.addView(tableRow)
                        }
                    }
                }
            }
            "Shop" -> {
                val columnNames = arrayOf("id", "region", "city", "address")
                addColumnNamesToTable(table, columnNames, view)
                
                GlobalScope.launch(Dispatchers.IO) { 
                    val database = SupermarketDatabase.getDatabase(view.context)
                    val shopDao = database.shopDao()
                    val shops = shopDao.getAll()
                    
                    launch(Dispatchers.Main) {
                        for (shop in shops) {
                            val tableRow = TableRow(view.context)
                            val textViews = arrayOf(
                                createTextView(view.context, params, shop.shopId.toString()),
                                createTextView(view.context, params, shop.region),
                                createTextView(view.context, params, shop.city),
                                createTextView(view.context, params, shop.address),

                            )
                            addTextViewsToTableRow(tableRow, textViews)

                            table.addView(tableRow)
                        }
                    }
                }
            }
            "Product" -> {
                val columnNames = arrayOf("id", "name", "category", "price", "manufacturer country")
                addColumnNamesToTable(table, columnNames, view)
                
                GlobalScope.launch(Dispatchers.IO) {
                    val database = SupermarketDatabase.getDatabase(view.context)
                    val productDao = database.productDao()
                    val products = productDao.getAll()
                    
                    launch(Dispatchers.Main) {
                        for (product in products) {
                            val tableRow = TableRow(view.context)
                            val textViews = arrayOf(
                                createTextView(view.context, params, product.productId.toString()),
                                createTextView(view.context, params, product.productName),
                                createTextView(view.context, params, product.category),
                                createTextView(view.context, params, product.price.toString()),
                                createTextView(view.context, params, product.manufacturerCountry)
                            )
                            addTextViewsToTableRow(tableRow, textViews)

                            table.addView(tableRow)
                        }    
                    }
                }
            }
            "ProductQuantity" -> {
                val columnNames = arrayOf("shopId", "productId", "quantity")
                addColumnNamesToTable(table, columnNames, view)
                
                GlobalScope.launch(Dispatchers.IO) {
                    val database = SupermarketDatabase.getDatabase(view.context)
                    val productQuantityDao = database.productQuantityDao()
                    val productQuantities = productQuantityDao.getAll()
                    
                    launch(Dispatchers.Main) {
                        for (productQuantity in productQuantities) {
                            val tableRow = TableRow(view.context)
                            val textViews = arrayOf(
                                createTextView(view.context, params, productQuantity.shopId.toString()),
                                createTextView(view.context, params, productQuantity.productId.toString()),
                                createTextView(view.context, params, productQuantity.quantity.toString()),
                            )
                            addTextViewsToTableRow(tableRow, textViews)

                            table.addView(tableRow)
                        }
                    }
                }
            }
        }

        return view
    }

    private fun createTextView(context: Context, params: ViewGroup.LayoutParams, text: String): TextView {
        val textView = TextView(context)
        textView.layoutParams = params
        textView.text = text
        textView.gravity = Gravity.CENTER
        return textView
    }

    private fun addTextViewsToTableRow(tableRow: TableRow, textViews: Array<TextView>) {
        for (textView in textViews) {
            tableRow.addView(textView)
        }
    }

    private fun addColumnNamesToTable(table: TableLayout, columnNames: Array<String>, view: View) {
        val idTextView = view.findViewById<TextView>(R.id.id)
        idTextView.text = columnNames[0]
        val params = idTextView.layoutParams
        val columnNamesRow = view.findViewById<TableRow>(R.id.column_names)

        for (i in 1 until columnNames.size) {
            val columnNameTextView = createTextView(view.context, params, columnNames[i])
            columnNamesRow.addView(columnNameTextView)
        }

    }

}