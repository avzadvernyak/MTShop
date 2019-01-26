package kampukter.mtshop.ui

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kampukter.mtshop.data.Cart
import kotlinx.android.synthetic.main.cart_item.view.*

class CartViewHolder(cartView: View) : RecyclerView.ViewHolder(cartView) {


    fun bind(item: Cart, itemClickListener: ItemClickListener<Cart>?) {
        with(itemView) {
            idTextView.text = item.name
            countTextView.text = item.count.toString()
//            checkBoxDel.isChecked = viewModel.checkMarkedItem(item.id)

            setOnClickListener { itemClickListener?.invoke(item) }

            checkBoxDel.setOnClickListener {
                Log.d("blablabla", "-----------------  "+item.id.toString()+"  -----------------")
                Log.d("blablabla", "-----------------  "+checkBoxDel.isChecked+"  -----------------")
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