package vsm.hse.practice3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        val showButton = view.findViewById<Button>(R.id.show)
        showButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_showFragment)
        }

        val addButton = view.findViewById<Button>(R.id.add)
        addButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        val deleteButton = view.findViewById<Button>(R.id.delete)
        deleteButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_deleteFragment)
        }

        return view
    }
}