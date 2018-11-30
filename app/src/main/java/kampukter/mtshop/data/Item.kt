package kampukter.mtshop.data

import com.google.gson.annotations.SerializedName

class Item {
    @SerializedName("id")
    var id: Long = 0

    @SerializedName("shortname")
    var shortname: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("price")
    var price: Int = 0

    @SerializedName("image")
    var image: String? = null

    @SerializedName("availability")
    var availability: Int = 0
}