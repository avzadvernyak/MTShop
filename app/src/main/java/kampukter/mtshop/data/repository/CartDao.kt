package kampukter.mtshop.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kampukter.mtshop.data.Cart

@Dao
interface CartDao {
    @Query(
        """select cart_Items.id as id, cart_Items.cartItemsCount as count, name, price, availability, image
        from cart_Items, items_table
        where cart_Items.id == items_table.id"""
    )
    fun getCart(): LiveData<List<Cart>>

}