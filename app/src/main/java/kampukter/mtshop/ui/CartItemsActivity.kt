package kampukter.mtshop.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kampukter.mtshop.R
import kampukter.mtshop.viewmodel.ItemSearchViewModel
import kotlinx.android.synthetic.main.cart_items.*
import java.util.concurrent.Executors

class CartItemsActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ItemSearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) supportFragmentManager.beginTransaction().add(
            R.id.cartRecyclerFragment,
            CartViewFragment()
        ).commit()

        setContentView(R.layout.cart_items)

        setSupportActionBar(toolbarCart)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Корзина"

        viewModel.countItemsInCart.observe(this, Observer {
            //Log.d("blablabla", "------- "+it.toString()+" ------")
            textViewCountItems?.text = it.toString()
        })

        viewModel.allItemsFromCart.observe(this, Observer {
            if (it.isNotEmpty()) {
                checkBoxDelAll.setOnClickListener {
                    if (checkBoxDelAll.isChecked) viewModel.addAllItemsMarked() else viewModel.delAllItemsMarked()
                }
            }
        })

        viewModel.markItemsCart.observe(this, Observer {
            if (viewModel.allItemsFromCart.value?.count() != it.count())
                checkBoxDelAll.isChecked = false
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {

            R.id.del_items_cart -> {
                Executors.newSingleThreadExecutor().submit { viewModel.delItemsFromCart() }
                checkBoxDelAll.isChecked = false
                Log.d("blablabla", "------- Очистка помеченых элементов из Корзины ------")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}