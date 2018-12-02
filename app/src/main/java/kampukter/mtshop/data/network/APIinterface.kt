package kampukter.exampleretrofit

import kampukter.mtshop.data.Item
import kampukter.mtshop.data.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterface {
    @GET("mt.php?type=1")
    fun getItems(@Query("q") categoryItems: Int): Call<List<Item>>

    @GET("mt.php?type=2")
    fun getProductDescription(@Query("id") idProduct: Long): Call<Product>

    companion object Factory {
        val BASE_URL = "http://orbis.in.ua/api/"
        fun create(): APIinterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIinterface::class.java)
        }
    }
}