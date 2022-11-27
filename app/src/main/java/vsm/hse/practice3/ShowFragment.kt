package vsm.hse.practice3

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding
import database.Employee
import database.SupermarketDatabase

class ShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show, container, false)

        val tableName = ShowFragmentArgs.fromBundle(requireArguments()).tableName

        Toast.makeText(view.context, tableName, Toast.LENGTH_SHORT).show()

//        val table = view as TableLayout
//        val paddingInDp = 10
//        val paddingInPx = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            paddingInDp.toFloat(),
//            view.context.resources.displayMetrics
//        ).toInt()


        return view
    }
}