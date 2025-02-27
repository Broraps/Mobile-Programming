package mobileprogramming.raffiargianda

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Ambil referensi ke View
        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvTitle: TextView = findViewById(R.id.tv_item_name)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)

        val imageRes = intent.getIntExtra("image", 0)
        val title = intent.getStringExtra("title") ?: "Judul tidak tersedia"
        val description = intent.getStringExtra("description") ?: "Deskripsi tidak tersedia"

        if (imageRes == 0) {
            throw IllegalArgumentException("Gambar tidak valid atau tidak diterima dari intent!")
        }


        // Set data ke View
        imgPhoto.setImageResource(imageRes)
        tvTitle.text = title
        tvDescription.text = description
    }
}
