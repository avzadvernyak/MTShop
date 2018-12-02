package kampukter.mtshop.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kampukter.mtshop.R
import kampukter.mtshop.ui.ItemListFragment.Companion.EXTRA_MESSAGE
import kotlinx.android.synthetic.main.product_description.*

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.product_description)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        titleProductTextView.text = "tetetetetetetetetetetet"
    }
}