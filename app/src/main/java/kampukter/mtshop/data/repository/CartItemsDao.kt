package kampukter.mtshop.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kampukter.mtshop.data.CartItems

@Dao
interface CartItemsDao:BasicDao<CartItems> {

    @Query("select * from cart_Items")
    fun getAll(): List<CartItems>

    @Query("select COUNT(*) from cart_Items")
    fun getCount() : LiveData<Int>

    @Query("select COUNT(*) from cart_Items where id == :searchId")
    fun findId(searchId: Long) : Int

    @Query("delete from cart_Items where id in (:listId) " )
    fun delMarkList( listId: MutableSet<Long> )

    @Query("update cart_Items set cartItemsCount = :valCount where id == :idItem")
    fun updCountItem( idItem: Long , valCount:Int)
}