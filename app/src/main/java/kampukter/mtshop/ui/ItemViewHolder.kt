package kampukter.mtshop.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.GlideApp
import kampukter.mtshop.R
import kampukter.mtshop.data.Item
import kotlinx.android.synthetic.main.item_card.view.*

class ItemViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView)  {
    val inStock = arrayOf("","В наличии", "Нет на складе")
    fun bind(item: Item, itemClickListener: ItemClickListener<Item>?) {
        with(itemView) {
            shortNameTextView.text = item.name
            priceTextView.text = item.price.toString()
            availabilityTextView.text = inStock[item.availability]
            GlideApp.with(this)
                .load(item.image)
                .placeholder(R.drawable.ic_city)
                .into(itemImageView)
            setOnClickListener { itemClickListener?.invoke(item) }
        }
    }
}
// enum class inStock ( var state: String){"В наличии", "Нет на складе"}