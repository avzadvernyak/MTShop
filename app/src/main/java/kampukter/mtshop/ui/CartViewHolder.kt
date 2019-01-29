package kampukter.mtshop.ui

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.data.Cart
import kotlinx.android.synthetic.main.cart_item.view.*

class CartViewHolder(cartView: View) : RecyclerView.ViewHolder(cartView) {


    fun bind(
        item: Cart,
        itemClickListener: ItemClickListener<Cart>?,
        itemSelectionListener: ItemSelectionListener?,
        itemCountListener: ItemCountListener?,
        markedItem: Set<Long>
    ) {
        val inStock = arrayOf("","В наличии", "Нет на складе")
        with(itemView) {
            availability.text = inStock[item.availability]
            idTextView.text = item.name
            countTextView.text = item.count.toString()
            checkBoxDel.isChecked = markedItem.contains(item.id)

            setOnClickListener { itemClickListener?.invoke(item) }
            checkBoxDel.setOnCheckedChangeListener { _, isChecked ->
                itemSelectionListener?.invoke(item.id, isChecked)
            }
            button_dec.setOnClickListener {
                if (item.count>1) itemCountListener?.invoke(item.id, item.count-1) else checkBoxDel.isChecked=true
            }
            button_inc.setOnClickListener {
                checkBoxDel.isChecked=false
                itemCountListener?.invoke(item.id, item.count+1)
            }
        }
    }

    fun clear() {
        with(itemView) {
            idTextView.text = ""
            countTextView.text = ""
        }
    }
}