package kampukter.mtshop.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.data.Item
import kotlinx.android.synthetic.main.item_card.view.*

class ItemViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView)  {
    fun bind(item: Item, itemClickListener: ItemClickListener<Item>?) {
        with(itemView) {
            nameTextView.text = item.name

            setOnClickListener { itemClickListener?.invoke(item) }
        }
    }
}