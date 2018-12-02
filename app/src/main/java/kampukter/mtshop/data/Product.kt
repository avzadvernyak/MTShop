package kampukter.mtshop.data

import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("id")
    var id: Long = 0

    @SerializedName("description")
    var description: String? = null
}