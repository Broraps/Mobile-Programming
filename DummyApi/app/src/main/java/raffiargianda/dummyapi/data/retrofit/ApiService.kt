package raffiargianda.dummyapi.data.retrofit

import raffiargianda.dummyapi.data.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts(): Call<ProductResponse>
}
