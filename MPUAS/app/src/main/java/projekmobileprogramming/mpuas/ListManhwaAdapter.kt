package projekmobileprogramming.mpuas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListManhwaAdapter(private val listManhwa: ArrayList<Manhwa>) : RecyclerView.Adapter<ListManhwaAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_manhwa, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val manhwa = listManhwa[position]
        holder.imgPhoto.setImageResource(manhwa.photo)
        holder.tvName.text = manhwa.name
        holder.tvGenre.text = manhwa.genre
        holder.tvPenulis.text = manhwa.penulis
        holder.ratingBar.rating = manhwa.rating
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(manhwa, position)
        }
    }

    override fun getItemCount(): Int = listManhwa.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvGenre: TextView = itemView.findViewById(R.id.tv_item_genre)
        val tvPenulis: TextView = itemView.findViewById(R.id.tv_item_penulis)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Manhwa, position: Int)
    }
}
