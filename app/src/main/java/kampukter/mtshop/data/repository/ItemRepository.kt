package kampukter.mtshop.data.repository

import kampukter.exampleretrofit.APIinterface
import kampukter.mtshop.data.Item
import retrofit2.Callback
import androidx.lifecycle.MutableLiveData
import retrofit2.Call


object ItemRepository {

        private val apiService = APIinterface.create()

        fun getItem(): MutableLiveData<List<Item>> {

            val data = MutableLiveData<List<Item>>()
            val call = apiService.getItems()

            call.enqueue(object : Callback<List<Item>> {
                override fun onResponse(call: Call<List<Item>>, response: retrofit2.Response<List<Item>>?) {
                    val list = response?.body()
                    data.postValue( list )
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                   // Log.e("MainActivity", t.toString())
                }
            })
            return data
        }
}