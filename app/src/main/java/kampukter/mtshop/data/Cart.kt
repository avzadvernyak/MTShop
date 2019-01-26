package kampukter.mtshop.data

data class Cart (
    var id: Long,
    var count: Int,
    var name: String,
    var price: Int,
    var availability: Int,
    var image: String
)