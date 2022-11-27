package vsm.hse.practice3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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

                val add = view.findViewById<Button>(R.id.)
            }

        }


        val view = when(tableName) {
            "Employee" -> inflater.inflate(R.layout.fragment_add, container, false)
            "Shop" -> inflater.inflate(R.layout.fragment_add, container, false)
            "ProductQuantity" -> inflater.inflate(R.layout.fragment_add, container, false)
            "Product" -> inflater.inflate(R.layout.fragment_add, container, false)
            else -> throw Exception("Invalid table name")
        }



        return view
    }
}