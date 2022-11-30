package vsm.hse.practice3.database

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import database.SupermarketDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vsm.hse.practice3.R

class ShowShopsWithProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_shops_with_products, container, false)

        val idTextView = view.findViewById<TextView>(R.id.id)
        val params = idTextView.layoutParams

        GlobalScope.launch(Dispatchers.IO) {
            val database = SupermarketDatabase.getDatabase(view.context)
            val shopDao = database.shopDao()

            val shopsWithProducts = shopDao.getShopsWithProducts()

            launch(Dispatchers.Main) {
                val table = view.findViewById<TableLayout>(R.id.table)

                val columnNamesRow = view.findViewById<TableRow>(R.id.column_names)
                idTextView.text = "shopId"
                val columnNames = arrayOf("region", "city", "address", "productId", "quantity")
                for (columnName in columnNames) {
                    columnNamesRow.addView(createTextView(view.context, params, columnName))
                }

                for ((shop, productQuantity) in shopsWithProducts) {
                    val tableRow = TableRow(view.context)

                    val textViews = arrayOf(
                        createTextView(view.context, params, shop.shopId.toString()),
                        createTextView(view.context, params, shop.region),
                        createTextView(view.context, params, shop.city),
                        createTextView(view.context, params, shop.address),
                        createTextView(view.context, params, productQuantity.productId.toString()),
                        createTextView(view.context, params, productQuantity.quantity.toString()),
                    )

                    for (textView in textViews) {
                        tableRow.addView(textView)
                    }

                    table.addView(tableRow)
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

}