package vsm.hse.practice3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
                        var message = ""
                        message += "$id "
                        message += "$name "
                        message += "$surname "
                        message += "$patronymic "
                        message += "$position "
                        message += "$salary "
                        message += shopId


                        Toast.makeText(view.context, message, Toast.LENGTH_SHORT ).show()

                        /*GlobalScope.launch(Dispatchers.IO){

                        }*/
                    }
                }

                return view
            }
        }


//        val view = when(tableName) {
//            "Employee" -> inflater.inflate(R.layout.fragment_add, container, false)
//            "Shop" -> inflater.inflate(R.layout.fragment_add, container, false)
//            "ProductQuantity" -> inflater.inflate(R.layout.fragment_add, container, false)
//            "Product" -> inflater.inflate(R.layout.fragment_add, container, false)
//            else -> throw Exception("Invalid table name")
//        }

        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}