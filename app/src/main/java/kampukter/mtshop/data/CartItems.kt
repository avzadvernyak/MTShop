package kampukter.mtshop.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_Items")
data class CartItems (
    @PrimaryKey
    var id: Long = 0L,

    val cartItemsCount: Int
)