package kampukter.mtshop.data.repository

import androidx.room.Dao
import androidx.room.Query
import kampukter.mtshop.data.CartItems

@Dao
interface CartItemsDao:BasicDao<CartItems> {

    @Query("select * from cart_Items")
    fun getAll(): List<CartItems>
}