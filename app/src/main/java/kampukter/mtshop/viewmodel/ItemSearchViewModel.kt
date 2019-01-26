package kampukter.mtshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kampukter.mtshop.data.Cart
import kampukter.mtshop.data.CartItems
import kampukter.mtshop.data.Item
import kampukter.mtshop.data.repository.ItemRepository

class ItemSearchViewModel : ViewModel() {

    private val categoryItems = MutableLiveData<Int>()
    private val idItem = MutableLiveData<Long>()
    private val markedItemsInCart = mutableListOf<Long>()

    val item: LiveData<Item> =
        Transformations.switchMap(idItem) { id -> ItemRepository().getProductDescription(id) }

    val items: LiveData<List<Item>> =
        Transformations.switchMap(categoryItems) { category -> ItemRepository().getItem(category) }

    val countItemsInCart: LiveData<Int> = ItemRepository().getCountsItemInCart()

    val allItemsFromCart: LiveData<List<Cart>> = ItemRepository().getAllFromCart()

    fun getCategory(category: Int) {
        categoryItems.postValue(category)
    }

    fun getIdProduct(id: Long) {
        idItem.postValue(id)
    }

    fun findItemInCart(idItems: Long): LiveData<Int> {
        return ItemRepository().findIdInCart(idItems)
    }

    fun addMarkedItem(itemId: Long) {

        if (!markedItemsInCart.contains(itemId)) {
            markedItemsInCart += listOf(itemId)
        }
    }

    fun delMarkedItem(itemId: Long) {

        if (markedItemsInCart.contains(itemId)) {
            markedItemsInCart += listOf(itemId)
        }
    }

    fun checkMarkedItem(itemId: Long): Boolean {
        return markedItemsInCart.contains(itemId)
    }

    fun addAllItemsMarked() {
        //Transformations.map(allItemsFromCart,{items->items.forEach { it -> addMarkedItem(it.id)}})
        val items = allItemsFromCart.value
        if (!items.isNullOrEmpty()) {
            items.forEach { it -> addMarkedItem(it.id) }
        }

    }

    fun delAllItemsMarked() {
        markedItemsInCart.clear()
    }

    fun delItemsFromCart() {
        ItemRepository().delItemsCart(markedItemsInCart)
    }


}