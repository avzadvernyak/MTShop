package kampukter.mtshop.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import kampukter.mtshop.data.CartItems
import kampukter.mtshop.data.Item

@Database(version = 1, entities = [ Item::class, CartItems::class], exportSchema = false)
abstract class ItemsDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    abstract fun cartItemsDao(): CartItemsDao
}