package raffiargianda.dummyapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import raffiargianda.dummyapi.data.response.Product
import raffiargianda.dummyapi.databinding.ItemProductBinding

class ProductAdapter(private val onClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val products = ArrayList<Product>()

    fun submitList(newProducts: List<Product>?) {
        products.clear()
        if (newProducts != null) {
            products.addAll(newProducts)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.binding.titleText.text = product.title
        holder.binding.priceText.text = "$${product.price}"
        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            onClick(product)
        }
    }
}
