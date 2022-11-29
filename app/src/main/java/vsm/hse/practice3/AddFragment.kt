package vsm.hse.practice3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tableName = AddFragmentArgs.fromBundle(requireArguments()).tableName

        when(tableName) {
            "Employee" -> {
                val view = inflater.inflate(R.layout.fragment_add_employee, container, false)

                val addEmployee = view.findViewById<Button>(R.id.add_employee)
                addEmployee.setOnClickListener {

                    val idString: String = view.findViewById<EditText>(R.id.edittext_employee_id).text.toString()
                    val id = if (idString.isEmpty()) 0 else idString.toInt()

                    val name: String = view.findViewById<EditText>(R.id.edittext_employee_name).text.toString()
                    val surname: String = view.findViewById<EditText>(R.id.edittext_employee_surname).text.toString()
                    val patronymic: String = view.findViewById<EditText>(R.id.edittext_employee_patronymic).text.toString()
                    val position: String = view.findViewById<EditText>(R.id.edittext_employee_position).text.toString()

                    val salaryString: String = view.findViewById<EditText>(R.id.edittext_employee_salary).text.toString()
                    val salary: Double = if (salaryString.isEmpty()) 0.0 else salaryString.toDouble()

                    val shopIdString: String = view.findViewById<EditText>(R.id.edittext_employee_shop_id).text.toString()
                    val shopId: Int = if (shopIdString.isEmpty()) 0 else shopIdString.toInt()


                    if (name.isEmpty() || surname.isEmpty() || patronymic.isEmpty() || position.isEmpty() || shopIdString.isEmpty()) {
                        Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
                    } else {
                        GlobalScope.launch(Dispatchers.IO){
                            val database = SupermarketDatabase.getDatabase(view.context)
                            val employeeDao = database.employeeDao()
                            val employee = Employee(id, name, surname, patronymic, position, salary, shopId)

                            employeeDao.insert(employee)

                            launch(Dispatchers.Main) {
                                val message = "ADD $id $name $surname $patronymic $position $salary $shopId"
                                Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }

                val deleteEmployee = view.findViewById<Button>(R.id.delete_employee)
                deleteEmployee.setOnClickListener {
                    val idString: String = view.findViewById<EditText>(R.id.edittext_employee_id).text.toString()
                    val id = if (idString.isEmpty()) 0 else idString.toInt()

                    val name: String = view.findViewById<EditText>(R.id.edittext_employee_name).text.toString()
                    val surname: String = view.findViewById<EditText>(R.id.edittext_employee_surname).text.toString()
                    val patronymic: String = view.findViewById<EditText>(R.id.edittext_employee_patronymic).text.toString()
                    val position: String = view.findViewById<EditText>(R.id.edittext_employee_position).text.toString()

                    val salaryString: String = view.findViewById<EditText>(R.id.edittext_employee_salary).text.toString()
                    val salary: Double = if (salaryString.isEmpty()) 0.0 else salaryString.toDouble()

                    val shopIdString: String = view.findViewById<EditText>(R.id.edittext_employee_shop_id).text.toString()
                    val shopId: Int = if (shopIdString.isEmpty()) 0 else shopIdString.toInt()


                    if (name.isEmpty() || surname.isEmpty() || patronymic.isEmpty() || position.isEmpty() || shopIdString.isEmpty()) {
                        Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
                    } else {
                        GlobalScope.launch(Dispatchers.IO){
                            val database = SupermarketDatabase.getDatabase(view.context)
                            val employeeDao = database.employeeDao()
                            val employee = Employee(id, name, surname, patronymic, position, salary, shopId)

                            employeeDao.delete(employee)

                            launch(Dispatchers.Main) {
                                val message = "DELETE $id $name $surname $patronymic $position $salary $shopId"
                                Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }

                return view
            }
            "Shop" -> {
                val view = inflater.inflate(R.layout.fragment_add_shop, container, false)

                val addShop = view.findViewById<Button>(R.id.add_shop)
                addShop.setOnClickListener {
                    val shopIdString = view.findViewById<EditText>(R.id.edittext_shop_id).text.toString()
                    val shopId = if (shopIdString.isEmpty()) 0 else shopIdString.toInt()

                    val region = view.findViewById<EditText>(R.id.edittext_shop_region).text.toString()
                    val city = view.findViewById<EditText>(R.id.edittext_shop_city).text.toString()
                    val address = view.findViewById<EditText>(R.id.edittext_shop_address).text.toString()

                    if (region.isEmpty() || city.isEmpty() || address.isEmpty()) {
                        Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
                    } else {
                        GlobalScope.launch(Dispatchers.IO){
                            val database = SupermarketDatabase.getDatabase(view.context)
                            val shopDao = database.shopDao()
                            val shop = Shop(shopId, region, city, address)

                            shopDao.insert(shop)

                            launch(Dispatchers.Main) {
                                val message = "ADD $shopId $region $city $address"
                                Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }

                val deleteShop = view.findViewById<Button>(R.id.delete_shop)
                deleteShop.setOnClickListener {
                    val shopIdString = view.findViewById<EditText>(R.id.edittext_shop_id).text.toString()
                    val shopId = if (shopIdString.isEmpty()) 0 else shopIdString.toInt()

                    val region = view.findViewById<EditText>(R.id.edittext_shop_region).text.toString()
                    val city = view.findViewById<EditText>(R.id.edittext_shop_city).text.toString()
                    val address = view.findViewById<EditText>(R.id.edittext_shop_address).text.toString()

                    if (region.isEmpty() || city.isEmpty() || address.isEmpty()) {
                        Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
                    } else {
                        GlobalScope.launch(Dispatchers.IO){
                            val database = SupermarketDatabase.getDatabase(view.context)
                            val shopDao = database.shopDao()
                            val shop = Shop(shopId, region, city, address)

                            shopDao.delete(shop)

                            launch(Dispatchers.Main) {
                                val message = "DELETE $shopId $region $city $address"
                                Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }

                return view
            }
            "Product" -> {
                val view = inflater.inflate(R.layout.fragment_add_product, container, false)

                val addProduct = view.findViewById<Button>(R.id.add_product)
                addProduct.setOnClickListener {
                    val productIdString = view.findViewById<EditText>(R.id.edittext_product_id).text.toString()
                    val productId = if (productIdString.isEmpty()) 0 else productIdString.toInt()

                    val productName = view.findViewById<EditText>(R.id.edittext_product_name).text.toString()
                    val category = view.findViewById<EditText>(R.id.edittext_product_category).text.toString()

                    val priceString = view.findViewById<EditText>(R.id.edittext_product_price).text.toString()

                    val manufacturerCountry = view.findViewById<EditText>(R.id.edittext_product_manufacturer_country).text.toString()

                    if (productName.isEmpty() || category.isEmpty() || priceString.isEmpty() || manufacturerCountry.isEmpty()) {
                        Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
                    } else {
                        val price = priceString.toDouble()

                        GlobalScope.launch(Dispatchers.IO){
                            val database = SupermarketDatabase.getDatabase(view.context)
                            val productDao = database.productDao()
                            val product = Product(productId, productName, category, price, manufacturerCountry)

                            productDao.insert(product)

                            launch(Dispatchers.Main) {
                                val message = "ADD $productId $productName $category $price $manufacturerCountry"
                                Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }

                val deleteProduct = view.findViewById<Button>(R.id.delete_product)
                deleteProduct.setOnClickListener {
                    val productIdString = view.findViewById<EditText>(R.id.edittext_product_id).text.toString()
                    val productId = if (productIdString.isEmpty()) 0 else productIdString.toInt()

                    val productName = view.findViewById<EditText>(R.id.edittext_product_name).text.toString()
                    val category = view.findViewById<EditText>(R.id.edittext_product_category).text.toString()

                    val priceString = view.findViewById<EditText>(R.id.edittext_product_price).text.toString()

                    val manufacturerCountry = view.findViewById<EditText>(R.id.edittext_product_manufacturer_country).text.toString()

                    if (productName.isEmpty() || category.isEmpty() || priceString.isEmpty() || manufacturerCountry.isEmpty()) {
                        Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
                    } else {
                        val price = priceString.toDouble()

                        GlobalScope.launch(Dispatchers.IO){
                            val database = SupermarketDatabase.getDatabase(view.context)
                            val productDao = database.productDao()
                            val product = Product(productId, productName, category, price, manufacturerCountry)

                            productDao.delete(product)

                            launch(Dispatchers.Main) {
                                val message = "DELETE $productId $productName $category $price $manufacturerCountry"
                                Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }

                return view
            }
        }

        val view = inflater.inflate(R.layout.fragment_add_product_quantity, container, false)

        val addProductQuantity = view.findViewById<Button>(R.id.add_product_quantity)
        addProductQuantity.setOnClickListener {
            val shopIdString = view.findViewById<EditText>(R.id.edittext_product_quantity_shop_id).text.toString()
            val productIdString = view.findViewById<EditText>(R.id.edittext_product_quantity_product_id).text.toString()
            val quantityString = view.findViewById<EditText>(R.id.edittext_product_quantity).text.toString()

            if (shopIdString.isEmpty() || productIdString.isEmpty() || quantityString.isEmpty()) {
                Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
            } else {
                val shopId = shopIdString.toInt()
                val productId = productIdString.toInt()
                val quantity = quantityString.toInt()

                GlobalScope.launch(Dispatchers.IO) {
                    val database = SupermarketDatabase.getDatabase(view.context)
                    val productQuantityDao = database.productQuantityDao()
                    val productQuantity = ProductQuantity(shopId, productId, quantity)

                    productQuantityDao.insert(productQuantity)

                    launch(Dispatchers.Main) {
                        val message = "ADD $shopId $productId $quantity"
                        Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                    }
                }
            }
        }

        val deleteProductQuantity = view.findViewById<Button>(R.id.delete_product_quantity)
        deleteProductQuantity.setOnClickListener {
            val shopIdString = view.findViewById<EditText>(R.id.edittext_product_quantity_shop_id).text.toString()
            val productIdString = view.findViewById<EditText>(R.id.edittext_product_quantity_product_id).text.toString()
            val quantityString = view.findViewById<EditText>(R.id.edittext_product_quantity).text.toString()

            if (shopIdString.isEmpty() || productIdString.isEmpty() || quantityString.isEmpty()) {
                Toast.makeText(view.context, "Введите недостающие значения", Toast.LENGTH_SHORT ).show()
            } else {
                val shopId = shopIdString.toInt()
                val productId = productIdString.toInt()
                val quantity = quantityString.toInt()

                GlobalScope.launch(Dispatchers.IO) {
                    val database = SupermarketDatabase.getDatabase(view.context)
                    val productQuantityDao = database.productQuantityDao()
                    val productQuantity = ProductQuantity(shopId, productId, quantity)

                    productQuantityDao.delete(productQuantity)

                    launch(Dispatchers.Main) {
                        val message = "DELETE $shopId $productId $quantity"
                        Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()
                    }
                }
            }
        }

        return view
    }
}