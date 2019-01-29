package kampukter.mtshop.data.repository

import androidx.lifecycle.LiveData
import kampukter.exampleretrofit.APIinterface
import kampukter.mtshop.data.Item
import retrofit2.Callback
import androidx.lifecycle.MutableLiveData
import kampukter.mtshop.data.Cart
import kampukter.mtshop.data.CartItems
import kampukter.mtshop.ui.db
import retrofit2.Call
import java.util.concurrent.Executors

class ItemRepository {

    //private val _query = MutableLiveData<String>()
    private val apiService = APIinterface.create()

    fun getItem(categoryItem: Int): MutableLiveData<List<Item>> {

        val needSynchro = false
        val data = MutableLiveData<List<Item>>()
        if (needSynchro) {

            val call = apiService.getItems(categoryItem)
            call.enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: retrofit2.Response<List<Item>>?) {
                    val list = response?.body()
                    if (list != null) {
                        list.forEach { it.categoryItem = categoryItem }
                        Executors.newSingleThreadExecutor().submit {
                            db.itemDao().insertAll(list)
                        }
                        data.postValue(list)
                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    // Log.e("MainActivity", t.toString())
                }
            })
        } else {
            Executors.newSingleThreadExecutor().submit { data.postValue(db.itemDao().search(categoryItem)) }
        }
        return data
    }

    fun getProductDescription(idItem: Long): MutableLiveData<Item> {
        val data = MutableLiveData<Item>()
        val call = apiService.getProductDescription(idItem)
        call.enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: retrofit2.Response<Item>?) {
                val list = response?.body()
                data.postValue(list)
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                //Log.e("MainActivity", t.toString())
            }
        })
        return data
    }

    fun getCountsItemInCart(): LiveData<Int> {
        //val data : LiveData<Int>
        //Executors.newSingleThreadExecutor().submit {  data =  }
        return db.cartItemsDao().getCount()
    }

    fun getAllFromCart(): LiveData<List<Cart>> {
        //val data = MutableLiveData<List<Cart>>()
        //Executors.newSingleThreadExecutor().submit { data.postValue(db.cartDao().getCart()) }
        return db.cartDao().getCart()
    }

    fun findIdInCart(id: Long): MutableLiveData<Int> {
        val data = MutableLiveData<Int>()
        Executors.newSingleThreadExecutor().submit { data.postValue(db.cartItemsDao().findId(id)) }
        return data
    }

    fun delItemsCart(listId: MutableSet<Long>) {
        db.cartItemsDao().delMarkList(listId)
    }
    fun updCountItem( idItem:Long, countItem:Int){
        db.cartItemsDao().updCountItem(idItem, countItem )
    }
}