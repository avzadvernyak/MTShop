package kampukter.mtshop.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.R
import kampukter.mtshop.viewmodel.ItemSearchViewModel
import kotlinx.android.synthetic.main.cart_items.*
import kotlinx.android.synthetic.main.cart_recycler_fragment.*
import java.util.concurrent.Executors

class CartViewFragment : Fragment() {

    private var cartItemsAdapter: CartItemsAdapter? = null

    /*
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ItemSearchViewModel::class.java)
    }
*/
    private lateinit var viewModel: ItemSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ItemSearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        return inflater.inflate(R.layout.cart_recycler_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cartItemsAdapter = CartItemsAdapter({}, { id, isChecked ->
            if (isChecked) viewModel.addMarkedItem(id) else viewModel.delMarkedItem(id)
        }) { id, count ->
            Log.d("blablabla", id.toString() + "- " + count.toString() + " ------")
            Executors.newSingleThreadExecutor().submit { viewModel.setCountItem(id, count) }
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = cartItemsAdapter
        }
        viewModel.allItemsFromCart.observe(this, Observer { itemsInCart ->
            cartItemsAdapter?.setItems(itemsInCart)

        })
        viewModel.markItemsCart.observe(this, Observer {
            cartItemsAdapter?.setListMarkedItems(it)
        })

    }
}