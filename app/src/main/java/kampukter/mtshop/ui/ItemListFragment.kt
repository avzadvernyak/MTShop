package kampukter.mtshop.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kampukter.mtshop.R
import kampukter.mtshop.viewmodel.ItemSearchViewModel
import kotlinx.android.synthetic.main.item_list_fragment.*
import kotlinx.android.synthetic.main.mtshop_list_activity.*


private const val ARG_CITIES_SET = "ARG_CITIES_SET"

class ItemListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ItemSearchViewModel::class.java)
    }
    private var itemAdapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        return inflater.inflate(R.layout.item_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //
        itemAdapter = ItemAdapter { item ->
            startActivity(Intent(context, ProductActivity::class.java).apply {
                putExtra(
                    EXTRA_MESSAGE,
                    item.id.toString()
                )
            })
        }
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }

        arguments?.getInt(ARG_CITIES_SET)?.let { itemsSet ->
            viewModel.getCategory(itemsSet)
        }
        progressBarFragment.visibility = View.VISIBLE
        viewModel.items.observe(this, Observer { items ->
            itemAdapter?.setItems(items)
            progressBarFragment.visibility = View.GONE
        })
    }

    companion object {
        fun newInstance(itemsSet: Int): ItemListFragment {
            return ItemListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CITIES_SET, itemsSet)
                }
            }
        }

        const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    }
}