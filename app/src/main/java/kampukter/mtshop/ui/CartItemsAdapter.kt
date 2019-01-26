package kampukter.mtshop.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.R
import kampukter.mtshop.data.Cart

//typealias ItemClickListener<T> = (T) -> Unit

class CartItemsAdapter(private val itemClickListener: ItemClickListener<Cart>? = null) :
    RecyclerView.Adapter<CartViewHolder>() {

    private var items: List<Cart>? = null

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
            holder.bind(item, itemClickListener)
        } else {
            holder.clear()
        }
    }
    fun setItems(items: List<Cart>) {
        this.items = items
        notifyDataSetChanged()
    }
}