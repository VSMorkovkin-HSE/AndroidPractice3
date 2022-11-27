package vsm.hse.practice3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import database.SupermarketDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            SupermarketDatabase.getDatabase(applicationContext)
        }
    }
}