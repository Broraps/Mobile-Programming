package raffiargianda.dummyapi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import raffiargianda.dummyapi.data.response.Product
import raffiargianda.dummyapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getSerializableExtra("product") as? Product

        if (product != null) {
            binding.titleText.text = product.title
            binding.descriptionText.text = product.description
            Glide.with(this)
                .load(product.thumbnail)
                .into(binding.imageView)
        } else {
            Toast.makeText(this, "No product found!", Toast.LENGTH_SHORT).show()
        }
    }
}


