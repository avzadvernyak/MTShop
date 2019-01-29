package kampukter.mtshop.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.R
import kampukter.mtshop.data.Cart

//typealias ItemClickListener<T> = (T) -> Unit
typealias ItemSelectionListener = (Long, Boolean) -> Unit
typealias ItemCountListener = (Long, Int) -> Unit

class CartItemsAdapter(
    private val itemClickListener: ItemClickListener<Cart>? = null,
    private val itemSelectionListener: ItemSelectionListener? = null,
    private val itemCountListener: ItemCountListener? = null
) :
    RecyclerView.Adapter<CartViewHolder>() {

    private var items: List<Cart>? = null
    private var markItem: Set<Long> = setOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.cart_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items?.get(position)
        if (item != null) {
            holder.bind(item, itemClickListener, itemSelectionListener, itemCountListener, markItem)
        } else {
            holder.clear()
        }
    }

    fun setItems(items: List<Cart>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setListMarkedItems(markedItems: Set<Long>) {
        this.markItem = markedItems
        notifyDataSetChanged()
    }
}