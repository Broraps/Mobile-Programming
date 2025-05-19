package raffiargianda.dummyapi.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import raffiargianda.dummyapi.data.response.Product
import raffiargianda.dummyapi.data.response.ProductResponse
import raffiargianda.dummyapi.data.retrofit.ApiClient
import raffiargianda.dummyapi.databinding.ActivityMainBinding
import raffiargianda.dummyapi.ui.adapter.ProductAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter

    private var products = listOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductAdapter { product ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }
        // Ini yang WAJIB ditambahkan
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fetchProducts()

        binding.searchBar.addTextChangedListener {
            val query = it.toString()
            val filteredList = products.filter { product ->
                product.title.contains(query, ignoreCase = true)
            }
            adapter.submitList(filteredList)
        }
    }

    private fun fetchProducts() {
        ApiClient.instance.getProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    products = response.body()?.products ?: emptyList()
                    adapter.submitList(products)
                } else {
                    Log.e("API_ERROR", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("API_ERROR", "Failed to fetch: ${t.message}")
            }
        })
    }
}
