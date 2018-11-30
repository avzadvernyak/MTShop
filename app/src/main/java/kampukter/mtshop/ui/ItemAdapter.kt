package kampukter.mtshop.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.R
import kampukter.mtshop.data.Item

typealias ItemClickListener<T> = (T) -> Unit

class ItemAdapter(  private val itemClickListener: ItemClickListener<Item>? = null
): RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_card, parent, false)
        )
    }
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        items?.get(position)?.let { item ->
            holder.bind(item, itemClickListener)
        }
    }
    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}