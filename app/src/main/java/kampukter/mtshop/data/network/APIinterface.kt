package kampukter.exampleretrofit

import kampukter.mtshop.data.Item
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIinterface {
    @GET("mt.php")
    fun getItems(): Call<List<Item>>

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