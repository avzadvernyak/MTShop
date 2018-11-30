package kampukter.mtshop.ui

import android.os.Bundle
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
import kotlinx.android.synthetic.main.item_list_fragment.*


private const val ARG_CITIES_SET = "ARG_CITIES_SET"

class ItemListFragment: Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ItemSearchViewModel::class.java)
    }
    private var itemAdapter: ItemAdapter? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        return inflater.inflate(R.layout.item_list_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //
        itemAdapter = ItemAdapter()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
        viewModel.getItem().observe(this, Observer { items ->
            itemAdapter?.setItems(items)
        })
        //
        /*
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)

            adapter = ItemAdapter { item ->
                Log.d("blablabla", "selected city ${item.name}")
            }.apply {
                viewModel.items.observe( this@ItemListFragment, Observer { items ->
                    setItems(items)
                })

            }
        }*/
    }
    companion object {
        fun newInstance(itemsSet: Int): ItemListFragment {
            return ItemListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CITIES_SET, itemsSet)
                }
            }
        }

    }
}