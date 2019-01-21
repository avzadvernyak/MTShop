package kampukter.mtshop.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kampukter.mtshop.GlideApp
import kampukter.mtshop.R
import kampukter.mtshop.ui.ItemListFragment.Companion.EXTRA_MESSAGE
import kampukter.mtshop.viewmodel.ItemSearchViewModel
import kotlinx.android.synthetic.main.product_description.*


class ProductActivity : AppCompatActivity() {

    private var idSelectedItem: Long = 0

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ItemSearchViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_description)



        setSupportActionBar(toolbarProduct)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        idSelectedItem = intent.getStringExtra(EXTRA_MESSAGE).toLong()

        progressBar.visibility = View.VISIBLE
        textView5.visibility = View.GONE
        textView4.visibility = View.GONE
        toolbarProduct.visibility = View.GONE

        viewModel.item.observe(this, Observer {
            if (it.id != 0L) {
                nameProductTextView.text = it.name
                descriptionProduct.text = it.description
                priceProductTextView.text = it.price.toString()
                supportActionBar?.title = it.shortname
                GlideApp.with(this)
                    .load(it.image)
                    .placeholder(R.drawable.ic_plus)
                    .into(imageProduct)
                progressBar.visibility = View.GONE
                textView5.visibility = View.VISIBLE
                textView4.visibility = View.VISIBLE
                toolbarProduct.visibility = View.VISIBLE

                shopcart.setOnClickListener {_->
                    AlertDialog.Builder(this).setTitle("Добавить в корзину:")
                        .setMessage("Вами выбран ${it.shortname}\nпо цене ${it.price} рублей.\n Добавить в корзину?")
                        .setPositiveButton("Да") { _, _ ->
                            Toast.makeText(
                                applicationContext, "Товар добавлен в корзину",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .setNegativeButton("Нет"){ dialog, which -> this.finish() }
                        .create().show()
                }
            } else {

                progressBar.visibility = View.GONE
                AlertDialog.Builder(this).setTitle("Проблема").setMessage("Что-то пошло не так!")
                    .setPositiveButton("YES") { dialog, which -> finish() }.create().show()
            }
        })
        viewModel.getIdProduct(idSelectedItem)
    }


}