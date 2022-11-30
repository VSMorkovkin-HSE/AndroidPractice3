package vsm.hse.practice3

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

class ShowProductsByEnteredCategoryFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show_products_by_entered_category_fragement, container, false)

        val category = ShowProductsByEnteredCategoryFragementArgs.fromBundle(requireArguments()).category

        val idTextView = view.findViewById<TextView>(R.id.id)
        val params = idTextView.layoutParams

        GlobalScope.launch(Dispatchers.IO) {
            val database = SupermarketDatabase.getDatabase(view.context)
            val productDao = database.productDao()

            val products = productDao.getAllByCategory(category)

            launch(Dispatchers.Main) {
                val table = view.findViewById<TableLayout>(R.id.table)

                val columnNamesRow = view.findViewById<TableRow>(R.id.column_names)
                idTextView.text = "productId"
                val columnNames = arrayOf("name", "category", "price", "manufacturer country")
                for (columnName in columnNames) {
                    columnNamesRow.addView(createTextView(view.context, params, columnName))
                }

                for (product in products) {
                    val tableRow = TableRow(view.context)
                    val textViews = arrayOf(
                        createTextView(view.context, params, product.productId.toString()),
                        createTextView(view.context, params, product.productName),
                        createTextView(view.context, params, product.category),
                        createTextView(view.context, params, product.price.toString()),
                        createTextView(view.context, params, product.manufacturerCountry)
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