package raffiargianda.dummyapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import raffiargianda.dummyapi.data.response.Product
import raffiargianda.dummyapi.data.response.ProductResponse
import raffiargianda.dummyapi.data.retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private var fullProductList = listOf<Product>()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        ApiClient.instance.getProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    fullProductList = response.body()?.products ?: emptyList()
                    _products.postValue(fullProductList)
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("API_ERROR", "Fetch Failed: ${t.message}")
            }
        })
    }

    fun searchProducts(query: String) {
        val filtered = fullProductList.filter {
            it.title.contains(query, ignoreCase = true)
        }
        _products.postValue(filtered)
    }
}
