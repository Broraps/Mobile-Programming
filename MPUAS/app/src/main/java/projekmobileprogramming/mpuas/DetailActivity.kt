package projekmobileprogramming.mpuas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data = intent.getParcelableExtra<Manhwa>("MANHWA_DATA")
        val position = intent.getIntExtra("POSITION", 0)

        val imgDetail: ImageView = findViewById(R.id.img_item_photo)
        val tvDetailName: TextView = findViewById(R.id.tv_item_name)
        val tvDetailGenre: TextView = findViewById(R.id.tv_item_genre)
        val tvDetailDeskripsi: TextView = findViewById(R.id.tv_item_deskripsi)
        val tvDetailPenulis: TextView = findViewById(R.id.tv_item_penulis)
        val btnRead: Button = findViewById(R.id.action_read)

        data?.let {
            imgDetail.setImageResource(it.photo)
            tvDetailName.text = it.name
            tvDetailGenre.text = it.genre
            tvDetailDeskripsi.text = it.deskripsi
            tvDetailPenulis.text = it.penulis

            supportActionBar?.title = it.name

            btnRead.setOnClickListener {
                val intent = Intent(this@DetailActivity, ReadActivity::class.java).apply {
                    putExtra("MANHWA_PHOTO_INDEX", position)
                }
                Log.d("DetailActivity", "Sending MANHWA_PHOTO_INDEX: $position")
                startActivity(intent)
            }
        } ?: run {
            // Log error if data is null
            Log.e("DetailActivity", "Failed to retrieve Manhwa data")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_share -> {
                val data = intent.getParcelableExtra<Manhwa>("MANHWA_DATA")
                data?.let {
                    shareContent(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareContent(data: Manhwa) {
        val shareText = "Manhwa rekomendasi untukmu : \n ${data.name}"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
