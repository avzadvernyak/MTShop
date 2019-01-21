package kampukter.mtshop.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items_table")
data class Item (
    @SerializedName("id")
    @PrimaryKey
    var id: Long = 0L,

    var categoryItem: Int,

    @SerializedName("shortname")
    val shortname: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("image")
    val image: String? = null,

    @SerializedName("availability")
    val availability: Int,

    @SerializedName("description")
    val description: String? = null
)