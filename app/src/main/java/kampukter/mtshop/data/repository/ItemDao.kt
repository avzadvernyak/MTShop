package kampukter.mtshop.data.repository

import androidx.room.Dao
import androidx.room.Query
import kampukter.mtshop.data.Item

@Dao
interface ItemDao:BasicDao<Item> {

    @Query("select * from items_table where categoryItem=:category")
    fun search(category: Int): List<Item>

}